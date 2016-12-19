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
package edu.emory.mathcs.cs323.select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class DumbSelect<T extends Comparable<T>> extends AbstractSelect<T> {
	@Override
	public T max(List<T> list, int k) {
		// Check if k > list.size()
		throwIllegalArgumentException(list, k);
		List<T> copy = new ArrayList<>(list);
		T max = null;

		// Linear search for the maximum k times
		for (int i = 0; i < k; i++) {
			max = Collections.max(copy);
			copy.remove(max);
		}

		return max;
	}
}
