package Analyzer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.DescendingVisitor;
import org.apache.bcel.classfile.JavaClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import GraphStructure.Graph;
import GraphStructure.Vertex;
/**
 * Clase utilizada para analizar Jars a nivel de dependencias, entre jars internos y clases
 * @author Sebastian Alba
 * @author Randal Mendez
 * @author David Pereira
 * @author Jose Cespedes
 */
public class Analyzer {
	private Graph graph;
	
	public Analyzer(String jarName, String choice) throws JSONException, ClassNotFoundException, ClassFormatException, IOException {
		JSONObject obj;
		if(choice.equals("JAR")) {	
			obj = analyzeJar(jarName);
			System.out.println(obj.toString(2));
			this.setGraph(setUpJarGraph(obj));
		}
		else{
			obj = analyzeClass(jarName);
			System.out.println(obj.toString(2));
			this.setGraph(setUpClassGraph(obj));
		}
	}
	
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	@SuppressWarnings("resource")
	private JSONObject analyzeJar(String jarName) {
		JSONArray listOfJar = new JSONArray();
		JSONObject object = new JSONObject();
		try {
			listOfJar.put((new JSONObject()).put("Jar", jarName).put("Name", jarName.substring(jarName.lastIndexOf("\\")).substring(1).replace(".jar", "")));
			JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
			JarEntry jar;
			while (true) {
				JSONObject jsonObj = new JSONObject();
				jar = jarFile.getNextJarEntry();
				if (jar == null) {
					break;
				}
				if(jar.getName().endsWith(".jar")) {
					File jF = new File(jarName, jar.getName());
					String jarFileName = jar.getName().replaceAll("/", "\\.");
					String myJar = jarFileName.substring(0, jarFileName.lastIndexOf('.'));
					jsonObj.put("Name", myJar);
					jsonObj.put("Jar", jF);
					listOfJar.put(jsonObj);
				}
			}
			object.put("Jar File Name", jarName);
			object.put("List of Jar", listOfJar);
		} catch (Exception e) {
			System.out.println("Oops.. Encounter an issue while parsing jar" + e.toString());
		}
		return object;
	}
	/**
	 * metodo que extrae las clases que hay dentro del archivo JAR
	 * @param jarName
	 * @return Json Object
	 */
	@SuppressWarnings("resource")
	public JSONObject analyzeClass(String jarName) {
		JSONArray listOfClasses = new JSONArray();
		JSONObject object = new JSONObject();
		try {
			File file = new File(jarName);
			if(!file.exists()) {
				JarFile tempF = new JarFile (jarName.substring(0, jarName.indexOf(file.getName())));
				JarEntry jE = tempF.getJarEntry(file.getName());
				if((new File(file.getParent()).getPath().endsWith(".jar"))) {
					file = extractJarFileFromJar((new File(file.getParent()).getParent()), tempF, jE);
					System.out.println(file.getPath());
					return analyzeClass(file.getPath());
				}
			}
			JarFile jF = new JarFile(file);
			Enumeration<JarEntry> e = jF.entries();
			JarEntry jar;
			while (e.hasMoreElements()) {
				JSONObject jsonObj = new JSONObject();
				jar = (JarEntry) e.nextElement();
				if (jar == null) {
					break;
				}
				if ((jar.getName().endsWith(".class"))) {
					String className = jar.getName().replaceAll("/", "\\.");
					String myClass = className.substring(0, className.lastIndexOf('.'));
					ClassParser classParsed = new ClassParser(jarName, jar.getName());
					jsonObj.put("Name", myClass);
					jsonObj.put("Class", classParsed);
					listOfClasses.put(jsonObj);
				}
			}
			object.put("Jar File Name", jarName);
			object.put("List of Class", listOfClasses);
		}catch(Exception e) {
			System.out.println("Oops.. Encounter an issue while parsing jar" + e);
		}
		return object;
	}
	/**
	 * Configura el grafo de los JARS
	 * @param obj
	 * @return JSON Object
	 * @throws JSONException
	 */
	private Graph setUpJarGraph(JSONObject obj) throws JSONException {
		Graph graph = new Graph();
		graph.setDirected(true);
		Vertex center = new Vertex(null);
		int n = 0;
		JSONArray listOfJar = obj.getJSONArray("List of Jar");
		for (int i = 0; i < listOfJar.length(); i++) {
			String name = (String) ((JSONObject) listOfJar.get(i)).get("Name");
			String path = ((JSONObject) listOfJar.get(i)).get("Jar").toString();
			File file = new File(path);
			Vertex vertex;
			if(i ==  0) {
				vertex = new Vertex(path, name, 375, 300);
				center = vertex;
			}else {
				int x = Integer.valueOf((int) Math.round(425 + Math.cos(n)*(listOfJar.length()*35)));
				int y = Integer.valueOf((int) Math.round(300 + Math.sin(n)*(listOfJar.length()*35)));
				vertex = new Vertex(file, name, x, y);
				graph.addEdges(center, vertex, 0, false);
				n += (3*Math.PI)/2;
			}
			graph.addVertex(vertex);
		}
		return graph;
	}
	/**
	 * Configura la clase de los JARS
	 * @param obj
	 * @return JSON Obejct
	 * @throws JSONException
	 */
	private Graph setUpClassGraph(JSONObject obj) throws JSONException, ClassFormatException, IOException {
		Graph graph = new Graph();
		graph.setDirected(true);
		
		JSONArray listOfClass = obj.getJSONArray("List of Class");
		float n = 0;
		int mult = 75;
		try {
			for (int i = 0; i < listOfClass.length(); i++) {
				String name = (String) ((JSONObject) listOfClass.get(i)).get("Name");
				
			    int x = Integer.valueOf((int) Math.round(800 + Math.cos(n)*mult));
				int y = Integer.valueOf((int) Math.round(900 + Math.sin(n)*mult));
				
				Vertex vertex = new Vertex(name, name, x, y);
				graph.addVertex(vertex);
				
				mult += 25;
				n += 2.25;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return drawDependencies(graph, listOfClass, mult, n);
	}
	
	private Graph drawDependencies(Graph graph, JSONArray listOfClass, int mult, float n) throws JSONException, ClassFormatException, IOException {
		for (int i = 0; i < listOfClass.length(); i++) {
			String name = (String) ((JSONObject) listOfClass.get(i)).get("Name");
			Vertex vertex = graph.getVertex(name);
			
			ClassParser classP = (ClassParser) ((JSONObject) listOfClass.get(i)).get("Class");
			JavaClass jClass = classP.parse();
			DependencyEmitter visitor = new DependencyEmitter(jClass);
		    DescendingVisitor classWalker = new DescendingVisitor(jClass, visitor);
		    classWalker.visit();
		    
			JSONArray arr = visitor.getJsonArr();
			for (int j = 1; j < arr.length(); j++) {
				String str = arr.getString(j);
				Vertex vert = graph.getVertex(str);
				if(vert == null && !str.contains("[") && !str.contains("java.lang")) {
					int x = Integer.valueOf((int) Math.round(800 + Math.cos(n)*mult));
					int y = Integer.valueOf((int) Math.round(900 + Math.sin(n)*mult));
					vert = new Vertex(str, str, x, y);
					graph.addVertex(vert);
					graph.addEdges(vertex, vert, 0, false);
					
					mult += 25;
					n += 2.25;
				}
				if (!str.contains("java.lang") && !str.contains("[") && vert != null) {
					graph.addEdges(vertex, vert, 0, false);
				}
			}
		}
		return graph;
	}
	/**
	 * Metodo que extrae jars internos del principal
	 * @param extractPath
	 * @param parentJar
	 * @param extractee
	 * @throws IOException
	 */
    private File extractJarFileFromJar(String extractPath, final JarFile parentJar, final JarEntry extractee) throws IOException {
        BufferedInputStream is = new BufferedInputStream(parentJar.getInputStream(extractee));

        File f = new File(extractPath + File.separator + extractee.getName());
        String parentName = f.getParent();
        if (parentName != null) {
            File dir = new File(parentName);
            dir.mkdirs();
        }
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(f));

        int c;
        while ((c = is.read()) != -1) {
            os.write((byte) c);
        }
        is.close();
        os.close();

        return f;
}
}
