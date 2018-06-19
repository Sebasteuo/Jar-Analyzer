package Analyzer;

import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.JavaClass;
import org.json.JSONArray;

public class DependencyEmitter extends EmptyVisitor {
	private JavaClass javaClass;
	private JSONArray arr;
	  
    public DependencyEmitter(JavaClass javaClass) {
        this.javaClass = javaClass;
        this.arr = new JSONArray();
    }
	  
    public JSONArray getJsonArr() {
    	//System.out.println("Tamano: " + arr.length());
    	return arr;
    }
    
	@Override
	public void visitConstantClass(ConstantClass obj) {
	    ConstantPool cp = javaClass.getConstantPool();
	    String bytes = obj.getBytes(cp).replaceAll("/", ".");
	    arr.put(bytes); 
	    //System.out.println(bytes);
	}
}
