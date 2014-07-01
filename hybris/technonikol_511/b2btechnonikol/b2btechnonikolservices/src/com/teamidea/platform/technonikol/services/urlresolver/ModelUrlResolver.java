/**
 *
 */
package com.teamidea.platform.technonikol.services.urlresolver;

import java.util.SortedMap;
import java.util.TreeMap;


/**
 * @author Igor Bobko
 *
 */

public class ModelUrlResolver
{
	private static final String[] rusAlphabet = new String[]
	{ "а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч",
			"ш", "щ", "ъ", "ы", "ь", "э", "ю", "я" };

	private static final String[] engAlphabet = new String[]
	{ "a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "c",
			"ch", "sh", "sch", "", "y", "", "e", "yu", "ya" };

	public static String translitRustoEng(String str)
	{
		if (str == null) return str;
		final SortedMap<String,String> map = translitGetMap();
		str = str.toLowerCase();
		final String[] rus = str.split("");
		String key = "";
		String result = "";
		for (int i = 0; i < rus.length; i++)
		{
			key = rus[i];
			if (map.containsKey(key))
			{
				result += map.get(key);
			}
			else
			{
				result += key;
			}
		}
		return result;
	}

	public static SortedMap<String, String> translitGetMap()
	{
		final SortedMap<String, String> map = new TreeMap<String, String>();
		for (int i = 0; i < rusAlphabet.length; i++)
		{
			map.put(rusAlphabet[i], engAlphabet[i]);
		}
		return map;
	}
}
