package practice.datadriventesting;

public class GeneratingAlphaNumericString {
	public static void main(String[] args) {
        int n=20;
        
        String alphaNumbericString="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        
        StringBuffer sb=new StringBuffer(n);
        
        for(int i=0;i<n;i++) {
        	int index=(int)(alphaNumbericString.length()*Math.random());
        	sb.append(alphaNumbericString.charAt(index));
        }
        System.out.println(sb);
	}
}
