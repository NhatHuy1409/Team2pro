import java.util.*;

/*
JDice: Java Dice Rolling Program
Copyright (C) 2006 Andrew D. Hilton (adhilton@cis.upenn.edu)

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
*/

public class DiceParser {
    /* this is a helper class to manage the input "stream" */
    private static class StringStream {
        private StringBuffer buff;

        public StringStream(String s) {
            buff = new StringBuffer(s);
        }

        // bỏ qua khoảng trắng trong chuỗi đầu vào
        private void munchWhiteSpace() {
            int index = 0;
            char curr;
            while (index < buff.length()) {
                curr = buff.charAt(index);
                if (!Character.isWhitespace(curr))
                    break;
                index++;
            }
            buff = buff.delete(0, index);
        }

        // kiểm tra chuỗi đầu vào có trống hay không
        public boolean isEmpty() {
            munchWhiteSpace();
            return buff.toString().equals("");
        }

        // đọc một số nguyên từ chuỗi đầu vào
        public Integer getInt() {
            return readInt();
       

	public static Vector<DieRoll> parseRoll(String s){
    StringStream ss=new StringStream(s.toLowerCase()); // Tạo một StringStream và chuyển đổi tất cả các ký tự sang chữ thường
    Vector<DieRoll> v= parseRollInner(ss,new Vector<DieRoll>()); // Gọi hàm parseRollInner để phân tích cú pháp và trả về một danh sách các kết quả tung xúc xắc
    if(ss.isEmpty()) // Kiểm tra xem chuỗi đã được xử lý hoàn tất chưa
        return v;
    return null; // Nếu chuỗi chưa được xử lý hoàn tất, trả về null
}

private static Vector<DieRoll> parseRollInner(StringStream ss, Vector<DieRoll> v){
    Vector<DieRoll> r=parseXDice(ss); // Phân tích cú pháp và trả về một danh sách các cuộc tung xúc xắc được tạo từ các lệnh "XdY", trong đó X là số lần tung và Y là số mặt của xúc xắc
    if(r==null) {
        return null;
    }
    v.addAll(r); // Thêm danh sách các cuộc tung xúc xắc vào danh sách chung
    if(ss.checkAndEat(";")){ // Kiểm tra xem có thêm các lệnh tung xúc xắc khác không
        return parseRollInner(ss,v); // Nếu có, gọi đệ quy để phân tích cú pháp và thêm vào danh sách chung
    }
    return v;
}

private static Vector<DieRoll> parseXDice(StringStream ss) {
    StringStream saved=ss.save(); // Lưu lại trạng thái của StringStream để có thể khôi phục lại nếu cần thiết
    Integer x=ss.getInt(); // Đọc số lần tung từ chuỗi
    int num;
    if(x==null) {
        num=1; // Nếu không có số lần tung nào được cung cấp, mặc định là tung một lần
    }
    else {
        if(ss.checkAndEat("x")) { // Kiểm tra xem có ký tự "x" sau số lần tung không
            num=x; // Nếu có, số lần tung được đặt bằng số lần tung đã đọc được
        }
        else {
            num=1; // Nếu không có ký tự "x", mặc định là tung một lần
            ss.restore(saved); // Khôi phục lại trạng thái của StringStream để đọc lại số lần tung
        }
    }
    DieRoll dr=parseDice(ss); // Phân tích cú pháp và trả về một cuộc tung xúc xắc từ chuỗi
    if(dr==null) {
	    return null;
	}
	  Integer num=ss.getInt();
    int dsides;
    int ndice;
    if(num==null) {
        ndice=1;
    }
    else {
        ndice=num;
    }
    if(ss.checkAndEat("d")){
        num=ss.getInt();
        if(num==null)
            return null;
        dsides=num;
    }
    else {
        return null;
    }
    num=ss.readSgnInt();
    int bonus;
    if(num==null)
        bonus=0;
    else
        bonus=num;
    return new DieRoll(ndice,
                       dsides,
                       bonus);    
}

/**
 * Parses the "tail" part of a dice expression (after any & operators).
 * @param r1 the DieRoll object representing the roll parsed so far
 * @param ss the StringStream object to read the expression from
 * @return a DieRoll object representing the entire roll, or null if the expression is invalid
 */
private static DieRoll parseDTail(DieRoll r1, StringStream ss) {
    if(r1==null)
        return null;
    if(ss.checkAndEat("&")) {
        DieRoll d2=parseDice(ss);
        return parseDTail(new DiceSum(r1,d2),ss);
    }
    else {
        return r1;
    }
}

/**
 * Tests the parseRoll method on several input strings.
 * Prints the results of each roll to standard output.
 * @param s the input string to test
 */
private static void test(String s) {
    Vector<DieRoll> v=parseRoll(s);
    if(v==null)
	    System.out.println("Failure:"+s);
	else{
	    System.out.println("Results for "+s+":");
	    for(i=0;i<v.size();i++){
		DieRoll dr=v.get(i);
		System.out.print(v.get(i));
		System.out.print(": ");
		System.out.println(dr.makeRoll());
	    }
	}
    }
    public static void main(String[] args) {
	test("d6");
	test("2d6");
	test("d6+5");
	test("4X3d8-5");
	test("12d10+5 & 4d6+2");
	test("d6 ; 2d4+3");
	test("4d6+3 ; 8d12 -15 ; 9d10 & 3d6 & 4d12 +17");
        test("4d6 + xyzzy");
	test("hi");
	test("4d4d4");
    }

}
