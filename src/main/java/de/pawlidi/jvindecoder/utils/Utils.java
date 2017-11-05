/*
 * Copyright (C) 2017 Maximilian Pawlidi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.pawlidi.jvindecoder.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 
 * @author pawlidim
 *
 */
public final class Utils {

	/**
	 * Invisible constructor.
	 */
	private Utils() {
		super();
	}

	/**
	 * <p>
	 * Checks if a string is not blank and have specified length.
	 * </p>
	 * 
	 * <pre>
	 * Utils.haveLength(null,1)      = false
	 * Utils.haveLength("",1)        = false
	 * Utils.haveLength("foo",1)     = false
	 * Utils.haveLength("bar",3)     = true
	 * </pre>
	 * 
	 * @param string
	 *            the string to check, may be null
	 * @param length
	 * @return {@code true} if the string length is equal given length
	 */
	public static boolean haveLength(final String string, final int length) {
		return Utils.isNotBlank(string) && string.trim().length() == length;
	}

	/**
	 * <p>
	 * Checks if a string is empty (""), null or whitespace only.
	 * </p>
	 *
	 *
	 * <pre>
	 * Utils.isBlank(null)      = true
	 * Utils.isBlank("")        = true
	 * Utils.isBlank(" ")       = true
	 * Utils.isBlank("foo")     = false
	 * Utils.isBlank("  bar  ") = false
	 * </pre>
	 *
	 * @param string
	 *            the string to check, may be null
	 * @return {@code true} if the string is null, empty or whitespace only
	 */
	public static boolean isBlank(final String string) {
		return string == null || string.trim().length() == 0;
	}

	/**
	 * <p>
	 * Checks if a string is not empty (""), not null and not whitespace only.
	 * </p>
	 * 
	 * <pre>
	 * Utils.isNotBlank(null)      = false
	 * Utils.isNotBlank("")        = false
	 * Utils.isNotBlank(" ")       = false
	 * Utils.isNotBlank("foo")     = true
	 * Utils.isNotBlank("  bar  ") = true
	 * </pre>
	 *
	 * @param cs
	 *            the string to check, may be null
	 * @return {@code true} if the string is not empty and not null and not
	 *         whitespace only
	 */
	public static boolean isNotBlank(final String string) {
		return !isBlank(string);
	}

	/**
	 * <p>
	 * Truncates a String. This will turn "Now is the time for all good men" into
	 * "Now is the time for".
	 * </p>
	 *
	 * <p>
	 * Specifically:
	 * </p>
	 * <ul>
	 * <li>If {@code str} is less than {@code maxWidth} characters long, return
	 * it.</li>
	 * <li>Else truncate it to {@code substring(str, 0, maxWidth)}.</li>
	 * <li>If {@code maxWidth} is less than {@code 0}, throw an
	 * {@code IllegalArgumentException}.</li>
	 * <li>In no case will it return a String of length greater than
	 * {@code maxWidth}.</li>
	 * </ul>
	 *
	 * <pre>
	 * Utils.truncate(null, 0)       = null
	 * Utils.truncate(null, 2)       = null
	 * Utils.truncate("", 4)         = ""
	 * Utils.truncate("abcdefg", 4)  = "abcd"
	 * Utils.truncate("abcdefg", 6)  = "abcdef"
	 * Utils.truncate("abcdefg", 7)  = "abcdefg"
	 * Utils.truncate("abcdefg", 8)  = "abcdefg"
	 * Utils.truncate("abcdefg", -1) = throws an IllegalArgumentException
	 * </pre>
	 *
	 * @param str
	 *            the String to truncate, may be null
	 * @param maxWidth
	 *            maximum length of result String, must be positive
	 * @return truncated String, {@code null} if null String input
	 */
	public static String truncate(final String str, final int maxWidth) {
		return truncate(str, 0, maxWidth);
	}

	/**
	 * <p>
	 * Truncates a String. This will turn "Now is the time for all good men" into
	 * "is the time for all".
	 * </p>
	 *
	 * <p>
	 * Works like {@code truncate(String, int)}, but allows you to specify a "left
	 * edge" offset.
	 *
	 * <p>
	 * Specifically:
	 * </p>
	 * <ul>
	 * <li>If {@code str} is less than {@code maxWidth} characters long, return
	 * it.</li>
	 * <li>Else truncate it to {@code substring(str, offset, maxWidth)}.</li>
	 * <li>If {@code maxWidth} is less than {@code 0}, throw an
	 * {@code IllegalArgumentException}.</li>
	 * <li>If {@code offset} is less than {@code 0}, throw an
	 * {@code IllegalArgumentException}.</li>
	 * <li>In no case will it return a String of length greater than
	 * {@code maxWidth}.</li>
	 * </ul>
	 *
	 * <pre>
	 * Utils.truncate(null, 0, 0) = null
	 * Utils.truncate(null, 2, 4) = null
	 * Utils.truncate("", 0, 10) = ""
	 * Utils.truncate("", 2, 10) = ""
	 * Utils.truncate("abcdefghij", 0, 3) = "abc"
	 * Utils.truncate("abcdefghij", 5, 6) = "fghij"
	 * Utils.truncate("raspberry peach", 10, 15) = "peach"
	 * Utils.truncate("abcdefghijklmno", 0, 10) = "abcdefghij"
	 * Utils.truncate("abcdefghijklmno", -1, 10) = throws an IllegalArgumentException
	 * Utils.truncate("abcdefghijklmno", Integer.MIN_VALUE, 10) = "abcdefghij"
	 * Utils.truncate("abcdefghijklmno", Integer.MIN_VALUE, Integer.MAX_VALUE) = "abcdefghijklmno"
	 * Utils.truncate("abcdefghijklmno", 0, Integer.MAX_VALUE) = "abcdefghijklmno"
	 * Utils.truncate("abcdefghijklmno", 1, 10) = "bcdefghijk"
	 * Utils.truncate("abcdefghijklmno", 2, 10) = "cdefghijkl"
	 * Utils.truncate("abcdefghijklmno", 3, 10) = "defghijklm"
	 * Utils.truncate("abcdefghijklmno", 4, 10) = "efghijklmn"
	 * Utils.truncate("abcdefghijklmno", 5, 10) = "fghijklmno"
	 * Utils.truncate("abcdefghijklmno", 5, 5) = "fghij"
	 * Utils.truncate("abcdefghijklmno", 5, 3) = "fgh"
	 * Utils.truncate("abcdefghijklmno", 10, 3) = "klm"
	 * Utils.truncate("abcdefghijklmno", 10, Integer.MAX_VALUE) = "klmno"
	 * Utils.truncate("abcdefghijklmno", 13, 1) = "n"
	 * Utils.truncate("abcdefghijklmno", 13, Integer.MAX_VALUE) = "no"
	 * Utils.truncate("abcdefghijklmno", 14, 1) = "o"
	 * Utils.truncate("abcdefghijklmno", 14, Integer.MAX_VALUE) = "o"
	 * Utils.truncate("abcdefghijklmno", 15, 1) = ""
	 * Utils.truncate("abcdefghijklmno", 15, Integer.MAX_VALUE) = ""
	 * Utils.truncate("abcdefghijklmno", Integer.MAX_VALUE, Integer.MAX_VALUE) = ""
	 * Utils.truncate("abcdefghij", 3, -1) = throws an IllegalArgumentException
	 * Utils.truncate("abcdefghij", -2, 4) = throws an IllegalArgumentException
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @param offset
	 *            left edge of source String
	 * @param maxWidth
	 *            maximum length of result String, must be positive
	 * @return truncated String, {@code null} if null String input
	 */
	public static String truncate(final String str, final int offset, final int maxWidth) {
		if (offset < 0) {
			throw new IllegalArgumentException("offset cannot be negative");
		}
		if (maxWidth < 0) {
			throw new IllegalArgumentException("maxWith cannot be negative");
		}
		if (str == null) {
			return null;
		}
		if (offset > str.length()) {
			return "";
		}
		if (str.length() > maxWidth) {
			final int ix = offset + maxWidth > str.length() ? str.length() : offset + maxWidth;
			return str.substring(offset, ix);
		}
		return str.substring(offset);
	}

	/**
	 * <p>
	 * Compares two CharSequences, returning {@code true} if they represent equal
	 * sequences of characters.
	 * </p>
	 *
	 * <p>
	 * {@code null}s are handled without exceptions. Two {@code null} references are
	 * considered to be equal. The comparison is case sensitive.
	 * </p>
	 *
	 * <pre>
	 * Utils.equals(null, null)   = true
	 * Utils.equals(null, "abc")  = false
	 * Utils.equals("abc", null)  = false
	 * Utils.equals("abc", "abc") = true
	 * Utils.equals("abc", "ABC") = false
	 * </pre>
	 *
	 * @see Object#equals(Object)
	 * @param cs1
	 *            the first CharSequence, may be {@code null}
	 * @param cs2
	 *            the second CharSequence, may be {@code null}
	 * @return {@code true} if the CharSequences are equal (case-sensitive), or both
	 *         {@code null}
	 */
	public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
		if (cs1 == cs2) {
			return true;
		}
		if (cs1 == null || cs2 == null) {
			return false;
		}
		if (cs1.length() != cs2.length()) {
			return false;
		}
		if (cs1 instanceof String && cs2 instanceof String) {
			return cs1.equals(cs2);
		}
		return regionMatches(cs1, false, 0, cs2, 0, cs1.length());
	}

	/**
	 * <p>
	 * Compares two CharSequences, returning {@code true} if they represent equal
	 * sequences of characters, ignoring case.
	 * </p>
	 *
	 * <p>
	 * {@code null}s are handled without exceptions. Two {@code null} references are
	 * considered equal. Comparison is case insensitive.
	 * </p>
	 *
	 * <pre>
	 * Utils.equalsIgnoreCase(null, null)   = true
	 * Utils.equalsIgnoreCase(null, "abc")  = false
	 * Utils.equalsIgnoreCase("abc", null)  = false
	 * Utils.equalsIgnoreCase("abc", "abc") = true
	 * Utils.equalsIgnoreCase("abc", "ABC") = true
	 * </pre>
	 *
	 * @param str1
	 *            the first CharSequence, may be null
	 * @param str2
	 *            the second CharSequence, may be null
	 * @return {@code true} if the CharSequence are equal, case insensitive, or both
	 *         {@code null}
	 */
	public static boolean equalsIgnoreCase(final CharSequence str1, final CharSequence str2) {
		if (str1 == null || str2 == null) {
			return str1 == str2;
		} else if (str1 == str2) {
			return true;
		} else if (str1.length() != str2.length()) {
			return false;
		} else {
			return regionMatches(str1, true, 0, str2, 0, str1.length());
		}
	}

	/**
	 * Green implementation of regionMatches.
	 *
	 * @param cs
	 *            the {@code CharSequence} to be processed
	 * @param ignoreCase
	 *            whether or not to be case insensitive
	 * @param thisStart
	 *            the index to start on the {@code cs} CharSequence
	 * @param substring
	 *            the {@code CharSequence} to be looked for
	 * @param start
	 *            the index to start on the {@code substring} CharSequence
	 * @param length
	 *            character length of the region
	 * @return whether the region matched
	 */
	static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart,
			final CharSequence substring, final int start, final int length) {
		if (cs instanceof String && substring instanceof String) {
			return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
		}
		int index1 = thisStart;
		int index2 = start;
		int tmpLen = length;

		// Extract these first so we detect NPEs the same as the
		// java.lang.String version
		final int srcLen = cs.length() - thisStart;
		final int otherLen = substring.length() - start;

		// Check for invalid parameters
		if (thisStart < 0 || start < 0 || length < 0) {
			return false;
		}

		// Check that the regions are long enough
		if (srcLen < length || otherLen < length) {
			return false;
		}

		while (tmpLen-- > 0) {
			final char c1 = cs.charAt(index1++);
			final char c2 = substring.charAt(index2++);

			if (c1 == c2) {
				continue;
			}

			if (!ignoreCase) {
				return false;
			}

			// The same check as in String.regionMatches():
			if (Character.toUpperCase(c1) != Character.toUpperCase(c2)
					&& Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
				return false;
			}
		}

		return true;
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public static boolean writeStringToFile(final String path, String... datas) {
		if (Utils.isBlank(path) || datas == null || datas.length == 0) {
			return false;
		}
		Path filePath = Paths.get(path);
		try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
			for (String data : datas) {
				writer.write(data);
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
