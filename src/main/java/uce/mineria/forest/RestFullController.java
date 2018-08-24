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
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forest")
public class RestFullController {

	@RequestMapping(value = "/exactitud/", method = RequestMethod.GET)
	public ResponseEntity<String> exactitud()
			throws RserveException, FileNotFoundException, IOException, REXPMismatchException {
		RConnection c = new RConnection("localhost", 6311);
		String retorno = null;
		if (c.isConnected()) {
			System.out.println("Connected to RServe.");

			REXP x;
			System.out.println("Reading script...");
			File file = new File("C:/Users/diaz_/OneDrive/Documentos/R/randomForest.R");
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				for (String line; (line = br.readLine()) != null;) {
					x = c.eval(line); // evaluates line in R
					if (x.inherits("try-error")) {
						System.out.println("R Serve Eval Exception : " + x.asString());
					}
					System.out.println(x.asNativeJavaObject());
					if (x instanceof REXPString) {
						System.out.println("Es un REXPString: " + x.asString());
					} else if (x instanceof REXPDouble) {
						if ("exactitudM_T".equals(line)) {
							retorno = String.valueOf(x.asDouble());
						}
						System.out.println("Es un REXPDouble: " + x.asDouble());
					} else if (x instanceof REXPNull || x instanceof REXPFactor) {
						System.out.println("REXPNull o REXPFactor");
					} else if (x instanceof REXPGenericVector) {
						if (x.isList()) {
							String[] keys = x.asList().keys();
							if (keys != null) {
								for (String key : keys) {
									if (key.equals("confusion")) {
										System.out.println(
												"key->" + key + "::value->" + x.asList().at(key).toDebugString());
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
		return new ResponseEntity<String>(retorno, HttpStatus.OK);
	}

	@RequestMapping(value = "/modelo/", method = RequestMethod.GET)
	public ResponseEntity<Collection<Modelo>> modelo()
			throws RserveException, FileNotFoundException, IOException, REXPMismatchException {
		RConnection c = new RConnection("localhost", 6311);
		double[][] retorno = null;
		Collection<Modelo> modelos = new ArrayList<>();
		if (c.isConnected()) {
			System.out.println("Connected to RServe.");

			REXP x;
			System.out.println("Reading script...");
			File file = new File("C:/Users/diaz_/OneDrive/Documentos/R/randomForest.R");
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				for (String line; (line = br.readLine()) != null;) {
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
										System.out.println(
												"key->" + key + "::value->" + x.asList().at(key).toDebugString());
										retorno = x.asList().at(key).asDoubleMatrix();
										ArrayList<String> reales = new ArrayList<>();
										reales.add("Negativo -1");
										reales.add("Neutro 0");
										reales.add("Positivo 1");
										for (int i = 0; i < 3; i++) {
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
		return new ResponseEntity<Collection<Modelo>>(modelos, HttpStatus.OK);
	}

}