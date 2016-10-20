package maxi_160514.picture;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import android.graphics.Bitmap;
public class MemoryCache {
	// 放入缓存时是个同步操作
	// LinkedHashMap构造方法的最后一个参数true代表这个map里的元素将按照最近使用次数由少到多排列，即LRU
	// 这样的好处是如果要将缓存中的元素替换，则先遍历出最近最少使用的元素来替换以提高效率
	private Map<String, Bitmap> cache = Collections
			.synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 1.5f, true));

	private long size = 0;// 缓存中图片所占用的字节，初始0，将通过此变量严格控制缓存所占用的堆内存
	private long limit = 86194304;// 缓存只能占用的最大堆内存

	public MemoryCache() {

	
		setLimit(Runtime.getRuntime().maxMemory() / 4); // use 25% of available
														// heap size
	}

	public void setLimit(long new_limit) {

		limit = new_limit;
//		System.out.println("MemoryCache will use up to " + limit / 1024.
	//			/ 1024. + "MB");
	}

	public Bitmap get(String id) {// 冲内存中读取图片
		try {
			if (!cache.containsKey(id))
				return null;
			return cache.get(id);
		} catch (NullPointerException ex) {
			return null;
		}
	}

	public void put(String id, Bitmap bitmap) {// 将图片放入缓存
		try {

			if (cache.containsKey(id)) {
				size -= getSizeInBytes(cache.get(id));
			}
			cache.put(id, bitmap);//无论存在不存在，都会讲图片压入缓存，以更新时间，
			size += getSizeInBytes(bitmap);
			checkSize();//每次压入都会检查，是否溢出
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}


	private void checkSize() {//严格控制堆内存，如果超过将首先替换最近最少使用的那个图片缓存

//		System.out.println("cache size=" + size + " length=" + cache.size());

		if (size > limit) {

			// 先遍历最近最少使用的元素

			Iterator<Entry<String, Bitmap>> iter = cache.entrySet().iterator();

			while (iter.hasNext()) {

				Entry<String, Bitmap> entry = iter.next();

				size -= getSizeInBytes(entry.getValue());

				iter.remove();

				if (size <= limit)

					break;

			}

	//		System.out.println("Clean cache. New size " + cache.size());

		}

	}
	public void clear() {
	//	System.out.println("Clean cache11:" + cache.size());
		cache.clear();
	//	System.out.println("Clean cache22:" + cache.size());

	}
	long getSizeInBytes(Bitmap bitmap) {// 计算图片占用的内存
		if (bitmap == null)
			return 0;
		return bitmap.getRowBytes() * bitmap.getHeight();
	}
}
