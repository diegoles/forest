package hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TwoStrings {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bufferRead.readLine());

		for (int i = 0; i < N; i++) {

			String[] texts = (bufferRead.readLine()).split(" ");

			if (texts[0].length() == (texts[1].length())) {
				if (texts[0].equals(texts[1])) {
					System.out.println("YES");
				} else {

					Map<String, Integer> letterTexts1 = new HashMap<>();
					Map<String, Integer> letterTexts2 = new HashMap<>();

					for (int x = 0; x < texts[0].length(); x++) {
						letterTexts1.put(String.valueOf(texts[0].charAt(x)), x);
						letterTexts2.put(String.valueOf(texts[1].charAt(x)), x);
					}

					if (letterTexts1.size() == (letterTexts2.size())) {

						String result1 = letterTexts1.entrySet().stream().map(entry -> entry.getKey())
								.collect(Collectors.joining());

						String result2 = letterTexts2.entrySet().stream().map(entry -> entry.getKey())
								.collect(Collectors.joining());

						if (verify(result1, result2)) {
							if (verify(texts[0], texts[1])) {
								System.out.println("YES");
							} else {
								System.out.println("NO");
							}
						} else {
							System.out.println("NO");
						}

					} else {
						System.out.println("NO");
					}

				}
			} else {
				System.out.println("NO");
			}

		}

	}

	private static Boolean verify(String texts1, String texts2) {
		int conEqual = 1;
		for (int x = 0; x < texts1.length(); x++) {

			if (x < texts1.length() - 1
					&& String.valueOf(texts1.charAt(x)).equals(String.valueOf(texts1.charAt(x + 1)))) {
				conEqual = conEqual + 1;
				continue;
			}

			for (int j = 0; j < conEqual; j++) {
				texts2 = texts2.replaceFirst(String.valueOf(texts1.charAt(x)), "");
			}

		}

		if (texts2.length() == 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

}
