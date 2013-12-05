package match;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: s0921122
 * Date: 13/08/28
 * Time: 19:42
 * To change this template use File | Settings | File Templates.
 */
public class CsvReader1 {
	public static void main(String args[]) {
		try {
			File csv = new File("C:\\Users\\tomo\\Desktop\\match\\matching.csv"); // CSVデータファイル

			BufferedReader br = new BufferedReader(new FileReader(csv));

			CsvReader1 csvr = new CsvReader1();
			ArrayList<String> input = new ArrayList<String>();

			// 最終行まで読み込む
			String line = "";
			while ((line = br.readLine()) != null) {
				// 1行をデータの要素に分割
				StringTokenizer st = new StringTokenizer(line, ",");

				while (st.hasMoreTokens()) {
					// 1行の各要素をタブ区切りで表示
					input.add(st.nextToken());
				}

				csvr.match_rendering(input);
				input.clear();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedReaderオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
		}
	}

	private void match_rendering(ArrayList<String> input) {
		// 出力
		ArrayList<String> in = new ArrayList<String>();
		// 正解セット
		ArrayList<String> ans = new ArrayList<String>();

		// 表情数の数を数える
		// HashSetにより重複が削除される
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(input);
		// スペースを除外して数える
		int rate = hs.size() - 1;
		hs.clear();


		// 中身を分ける
		int i = 0;
		while (true) { // 出力
			if (input.get(i).equals(" ")) break;
			in.add(input.get(i));
			System.out.println(input.get(i));
			i++;
		}
		System.out.println();
		i++;
		while (i < input.size()) { // 正解セット
			if (input.get(i).equals(" ")) continue;
			ans.add(input.get(i));
			System.out.println(input.get(i));
			i++;
		}

		// 正解数のカウント
		int ansCount = 0;
		boolean flag = false;

		for (String str : in) {
			for (String answer : ans) {
				if (str.equals(answer)) {
					// 正解した
					flag = true;
				}
			}
			if (flag) ansCount++;
			flag = false;
		}

		System.out.println();
		System.out.println("出力数　　　　 :" + in.size());
		System.out.println("正解セット数　 :" + ans.size());
		System.out.println("出てきた表情数 :" + rate);
		System.out.println("正解数:" + ansCount + " / 合計:" + (in.size() + ans.size()));
                float a = (in.size() + ans.size());
                a = ansCount /  a;
                System.out.println(a);
		System.out.println("-------------------");
		System.out.println();
	}
}
