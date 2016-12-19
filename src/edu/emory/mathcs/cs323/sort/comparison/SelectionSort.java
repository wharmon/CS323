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
package edu.emory.mathcs.cs323.sort.comparison;

import java.util.Comparator;

import edu.emory.mathcs.cs323.sort.AbstractSort;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {
	public SelectionSort() {
		this(Comparator.naturalOrder());
	}

	public SelectionSort(Comparator<T> comparator) {
		super(comparator);
	}

	@Override
	public void sort(T[] array, final int beginIndex, final int endIndex) {
		int min;

		for (int i = beginIndex; i < endIndex - 1; i++) {
			min = i;

			for (int j = i + 1; j < endIndex; j++) {
				if (compareTo(array, j, min) < 0)
					min = j;
			}

			swap(array, i, min);
		}
	}
}

// Number of comparisons: two loops -> n start indexes with n comparisons -> O(n^2)

// Number of swaps: search for min n times -> swap a single pair -> so n swaps -> O(n)

// Number of assignments: each swap counts as an assignment of two elements -> assignment of each element occurs once -> O(n)

