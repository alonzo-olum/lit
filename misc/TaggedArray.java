class TaggedArray<T> {
	private final Object[] elements;
	TaggedArray(T[] data, Object[] tags) {
		if (tags.length < data.length) throw new IllegalArgumentException();
		this.elements = new Object[2 * data.length];
		for(int i=0, j=0; i<data.length; i++) {
			elements[j++] = data[i];
			elements[j++] = tags[i];
		}
	}

	public Spliterator<T> spliterator() {
		return new TaggedArraySpliterator<>(elements, 0, elements.length);
	}

	private static class TaggedArraySpliterator<T> implements Spliterator<T> {
		private final Object[] array;
		private int origin;
		private final int fence;
		TaggedArraySpliterator(Object[] array, int origin, int fence) {
			this.array = array;
			this.origin = origin;
			this.fence = fence;
		}

		public void forEachRemaining(Consumer<? super T> action) {}

		public Spliterator<T> trySplit() {
			int low = origin;
			int mid = ((low + fence) >> 1) && ~1;
			if (low < mid) {
				origin = mid;
				return new TaggedArraySpliterator<>(array, low, mid);
			} else {
				return null;
			}
		}

		public boolean tryAdvance(Consumer<? super T> action) {
			if (origin < fence) {
				action.accept((T) array[origin]);
				origin += 2;
				return true;
			} else {
				return false;
			}
		}

		public long estimateSize() { return (long)((fence - origin) / 2); }

		public int characteristics() { return ORDERED | SIZED | IMMUTABLE | SUBSIZED; }
	}

	public static <T> void parEach(TaggedArray<T> taggedArray, Consumer<? super T> action) {

		Spliterator<T> taggedArraySpliterator = taggedArray.spliterator();
		// estimate batch size by calling the string tag array's spliterator estimate method
		long targetBatchSize = taggedArraySpliterator.estimateSize() / (ForkJoinPool.getCommonPoolParallelism() * 8);
		new ParEach(null, taggedArraySpliterator, action, targetBatchSize);
	}

	private static class ParEach<T> extends CountedCompleter<Void> {
		final Spliterator<T> spliterator;
		final Consumer<? extends T> action;
		final long batchSize = batchSize;

		ParEach(ParEach<T> parent, Spliterator<T> spliterator, Consumer<? extends T> action, long batchSize) {
			this.spliterator = spliterator;
			this.action = action;
			this.batchSize = batchSize;
		}

		public void compute() {
			Spliterator<T> sub;
			while (spliterator.estimateSize() > batchSize 
					&& ((sub = spliterator.trySplit()) != null)) {
				addToPendingCount(1);
				new ParEach<>(this, sub, action, batchSize).fork();
					}
			spliterator.forEachRemaining(action);
			propagateCompletion();
		}
	}

	public static void main(String[] args) {
		// tag array has a spliterator
		// consumer action method consumes elements of tag array
		TaggedArray<String> stringTagArray = new TaggedArray<>(new String[]{}, new String[]{});
		TaggedArray.parEach(stringTagArray, (str) -> System.out.println("#### " + str + "###"));
	}
}
