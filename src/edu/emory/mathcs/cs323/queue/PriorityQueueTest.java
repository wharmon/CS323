/**
 * Copyright 2014, Emory University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.emory.mathcs.cs323.queue;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Test;

import edu.emory.mathcs.cs323.utils.DSUtils;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class PriorityQueueTest {
	@Test
	public void testAccuracy() {
		testAccuracy(new LazyPriorityQueue<>(), Comparator.reverseOrder());
		testAccuracy(new EagerPriorityQueue<>(), Comparator.reverseOrder());
//		testAccuracy(new BinaryHeap<>(), Comparator.reverseOrder());
//
//		testAccuracy(new LazyPriorityQueue<Integer>(Comparator.reverseOrder()), Comparator.naturalOrder());
//		testAccuracy(new EagerPriorityQueue<Integer>(Comparator.reverseOrder()), Comparator.naturalOrder());
//		testAccuracy(new BinaryHeap<Integer>(Comparator.reverseOrder()), Comparator.naturalOrder());
		
		//testAccuracy(new TernaryHeap<>(), Comparator.reverseOrder());
		//testAccuracy(new BinaryHeap<>(), Comparator.reverseOrder());
	}

	void testAccuracy(AbstractPriorityQueue<Integer> q, Comparator<Integer> sort) {
		List<Integer> keys = DSUtils.toIntegerList(4, 1, 3, 2, 5, 6, 8, 3, 4, 7, 5, 9, 7);
		keys.forEach(key -> q.add(key));
		//System.out.println(q.toString());
		Collections.sort(keys, sort);
		keys.forEach(key -> assertEquals(key, q.remove()));

	}

	@Test
	@SuppressWarnings("unchecked")
	public void testSpeed() {
		testSpeed(new LazyPriorityQueue<>(), new EagerPriorityQueue<>());

	}

	@SuppressWarnings("unchecked")
	void testSpeed(AbstractPriorityQueue<Integer>... qs) {
		final int ITER = 10, WARM = 10, LENGTH = qs.length;

		StringBuilder build = new StringBuilder();
		long[][] times = new long[LENGTH][2];
		long[][] temp = new long[LENGTH][2];
		int[] keys;
		Random rand;

		for (int size = 100; size <= 5000; size += 100) {
			for (int k = 0; k < LENGTH; k++) {
				rand = new Random(size);

				for (int j = 0; j < WARM; j++) {
					keys = DSUtils.getRandomIntArray(rand, size);
					addRuntime(qs[k], temp[k], keys);
				}

				for (int j = 0; j < ITER; j++) {
					keys = DSUtils.getRandomIntArray(rand, size);
					addRuntime(qs[k], times[k], keys);
				}
			}

			build.append(size);

			for (long[] time : times)
				build.append("\t" + Arrays.stream(time).mapToObj(i -> Long.toString(i)).collect(Collectors.joining("\t")));

			build.append("\n");
		}

		System.out.println(build.toString());
	}

	void addRuntime(AbstractPriorityQueue<Integer> queue, long[] times, int[] keys) {
		long st, et;

		st = System.currentTimeMillis();

		for (int key : keys)
			queue.add(key);

		et = System.currentTimeMillis();
		times[0] += et - st;

		st = System.currentTimeMillis();

		while (!queue.isEmpty())
			queue.remove();

		et = System.currentTimeMillis();
		times[1] += et - st;
	}
}
