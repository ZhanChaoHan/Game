package myutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortedMap extends HashMap {

	private List sorted;
	private Comparator comp;

	public SortedMap() {
		this.sorted = new ArrayList();
	}

	public SortedMap(Comparator c) {
		this();
		this.comp = c;
	}

	public void clear() {
		super.clear();
		this.sorted.clear();
	}

	public Object put(Object key, Object value) {
		this.sorted.add(value);
		Collections.sort(this.sorted, this.comp);
		return super.put(key, value);
	}

	public void putAll(Map m) {
		Iterator var3 = m.entrySet().iterator();

		while (var3.hasNext()) {
			Entry e = (Entry) var3.next();
			this.sorted.add(e.getValue());
		}

		super.putAll(m);
	}

	public Object remove(Object key) {
		this.sorted.remove(super.get(key));
		return super.remove(key);
	}

	public void update(Object key) {
		Collections.sort(this.sorted, this.comp);
	}

	public List getSorted() {
		return this.sorted;
	}
}
