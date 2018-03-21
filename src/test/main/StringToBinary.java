
public class StringToBinary {
    public StringToBinary(){

    }


    public String toBinary(){
        String str = "1111.22018_01_23_18_53_01.jpeg772170";//"sw01000100018.8052018_01_23_18_53_01.jpeg9999";
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            result +=Integer.toBinaryString(strChar[i])+ " ";
        }
        System.out.println(result.length()+":***:"+result);
        return result;
    }

    //将二进制字符串转换成int数组
    public int[] BinstrToIntArray(String binStr) {
        char[] temp=binStr.toCharArray();
        int[] result=new int[temp.length];
        for(int i=0;i<temp.length;i++) {
            result[i]=temp[i]-48;
        }
        return result;
    }

    //将二进制转换成字符
    public char BinstrToChar(String binStr){
        int[] temp=BinstrToIntArray(binStr);
        int sum=0;
        for(int i=0; i<temp.length;i++){
            sum +=temp[temp.length-1-i]<<i;
        }
        return (char)sum;
    }
    public void BinstrToStr(String str){
        String binStr = str;
        String[] tempStr=binStr.split(" ");
        char[] tempChar=new char[tempStr.length];
        for(int i=0;i<tempStr.length;i++) {
            tempChar[i]=BinstrToChar(tempStr[i]);
        }
        System.out.println(String.valueOf(tempChar));
    }

}
