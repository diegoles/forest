package uce.mineria.forest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPFactor;
import org.rosuda.REngine.REXPGenericVector;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REXPNull;
import org.rosuda.REngine.REXPString;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class Forest {

	public static void main(String[] args) throws RserveException, FileNotFoundException, IOException, REXPMismatchException {
		RConnection c = new RConnection("localhost", 6311);
		Collection<Modelo> modelos = new ArrayList<>();
		if (c.isConnected()) {
			System.out.println("Connected to RServe.");

			REXP x;
			System.out.println("Reading script...");
			File file = new File("C:/Users/diaz_/OneDrive/Documentos/R/randomForest.R");
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				for (String line; (line = br.readLine()) != null;) {
//					System.out.println(line);
					x = c.eval(line); // evaluates line in R
					if (x.inherits("try-error")) {
						System.out.println("R Serve Eval Exception : " + x.asString());
					}
					System.out.println(x.asNativeJavaObject());
					if (x instanceof REXPString) {
						System.out.println("Es un REXPString: " + x.asString());
					} else if (x instanceof REXPDouble) {
						System.out.println("Es un REXPDouble: " + x.asDouble());
					} else if (x instanceof REXPNull || x instanceof REXPFactor) {
						System.out.println("REXPNull o REXPFactor");
					} else if (x instanceof REXPGenericVector) {
						if (x.isList()) {
							String[] keys = x.asList().keys();
							if (keys != null) {
								for (String key : keys) {
									if (key.equals("confusion")) {
										double[][] retorno = x.asList().at(key).asDoubleMatrix();
										ArrayList<String> reales = new ArrayList<>();
										reales.add("-1");
										reales.add("0");
										reales.add("1");
										for (int i=0; i < 3;i++) {
											Modelo modelo = new Modelo();
											modelo.setReal(reales.get(i));
											modelo.setNegativo(retorno[i][0]);
											modelo.setPositivo(retorno[i][1]);
											modelo.setNeutro(retorno[i][2]);
											modelo.setError(retorno[i][3]);
											modelos.add(modelo);
										}
										
									}
								}
							}
						}
					} else {
						System.out.println(x.asNativeJavaObject());
						for (Object object : x.asList()) {
							if (object instanceof REXPString) {
								System.out.println("Es un List: " + x.asString());
							}
						}
					}

				}
			}

		} else {
			System.out.println("Rserve could not connect");
		}

		c.close();
		System.out.println("Session Closed");
	}

}
