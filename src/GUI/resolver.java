package GUI;

import java.awt.Color;
import java.util.ArrayList;

public class resolver implements Runnable {

	public void solve() {

		Thread t = new Thread(this);

		t.start();

	}

	public void run() {

		System.out.println("CRUZ BLANCA ·········");
		cruz_blanca();
		System.out.println("CRUZ BLANCA ········· OK");
		System.out.println("ESQUINAS BLANCAS ········· ");
		esquinas_blancas();
		System.out.println("ESQUINAS BLANCAS ········· OK");
		System.out.println("ARISTAS LATERALES ········· ");
		aristas_laterales();
		System.out.println("ARISTAS LATERALES ········· OK");
		System.out.println("CRUZ AMARILLA ········· ");
		cruz_amarilla();
		System.out.println("CRUZ AMARILLA ········· OK");
		System.out.println("ARISTAS SUPERIORES ········· ");
		aristas_superiores();
		System.out.println("ARISTAS SUPERIORES ········· OK");
		System.out.println("PERMUTAR ESQUINAS ········· ");
		permutar_esquinas();
		System.out.println("PERMUTAR ESQUINAS ········· OK");
		System.out.println("GIRAR ESQUINAS ········· ");
		girar_esquinas();
		System.out.println("GIRAR ESQUINAS ········· OK ");
		finalizar();
		System.out.println("FINALIZADO ········· OK");

	}

	@SuppressWarnings("unchecked")
	private void cruz_blanca() {

		for (int i = 1; i < 5; i++) { // COLOCARA EN LA CARA BLANCA LAS ARISTAS BLANCAS QUE SE ENCUENTREN EN LAS CARAS
										// LATERALES

			cara_panel cara_color = piezas.getCara(i);

			arista a = piezas.getArista(i, 7);

			boolean rotar = true;

			if ((piezas.getID_Color(a.color1()) == 0 || piezas.getID_Color(a.color2()) == 0)
					&& (piezas.getID_Color(a.color1()) == i || piezas.getID_Color(a.color2()) == i)) { // SI LA CARA YA
																										// TIENE UNA
																										// ARISTA BLANCA
																										// BIEN
																										// COLOCADA, NO
																										// LA TOCARA

				if (piezas.getID_Color(a.color1()) == 1 && a.cara1() == 0) {

					System.out.println("Cara: " + i + " id: 7 bien colocada");

					rotar = false;

				} else if (piezas.getID_Color(a.color2()) == 0 && a.cara2() == 0) {

					rotar = false;

					System.out.println("Cara: " + i + " id: 7 bien colocada");

				}

			}

			if (rotar) {

				boolean colocar = false;

				int giros_derecha = 0;

				boolean prioritario = false;

				int giros_prioritarios = 0;

				for (int j = 1; j < 8; j += 2) {

					a = piezas.getArista(i, j);

					if (piezas.getID_Color(a.color1()) == 0 || piezas.getID_Color(a.color2()) == 0) { // SI LA CARA
																										// CONTIENE
																										// ALGUNA ARISTA
																										// BLANCA

						if (piezas.getID_Color(a.color1()) == 0 && a.cara1() != i) { // SI LA PARTE BLANCA ESTA EN LA
																						// CARA DE AL LADO

							colocar = true;
							if (j == 1)
								giros_derecha = 2;
							if (j == 3)
								giros_derecha = 3;
							if (j == 5)
								giros_derecha = 1;
							if (j == 7)
								giros_derecha = 0;

							System.out.println("Cara: " + i + " id: " + j + " hay arista blanca. Colocar");

							if (piezas.getID_Color(a.color2()) == i) { // SI TIENE UNA ARISTA BLANCA Y DEL COLOR DE SU
																		// CARA, TENDRA PRIORIDAD Y ROTARA PARA DEJARLA
																		// BIEN COLOCADA

								System.out.println("Arista prioritaria");

								prioritario = true;
								if (j == 1)
									giros_prioritarios = 2;
								if (j == 3)
									giros_prioritarios = 3;
								if (j == 5)
									giros_prioritarios = 1;
								if (j == 7)
									giros_prioritarios = 0;
							}

						} else if (piezas.getID_Color(a.color2()) == 0 && a.cara2() != i) {

							colocar = true;
							if (j == 1)
								giros_derecha = 2;
							if (j == 3)
								giros_derecha = 3;
							if (j == 5)
								giros_derecha = 1;
							if (j == 7)
								giros_derecha = 0;
							System.out.println("Cara: " + i + " id: " + j + " hay arista blanca. Colocar");

							if (piezas.getID_Color(a.color1()) == i) {

								System.out.println("Arista prioritaria");

								prioritario = true;
								if (j == 1)
									giros_prioritarios = 2;
								if (j == 3)
									giros_prioritarios = 3;
								if (j == 5)
									giros_prioritarios = 1;
								if (j == 7)
									giros_prioritarios = 0;
							}

						}

					}
				}

				if (colocar) {

					if (prioritario) {

						for (int j = 0; j < giros_prioritarios; j++) {
							cara_color.girar_derecha(true);
						}


					} else {

						for (int j = 0; j < giros_derecha; j++) {
							cara_color.girar_derecha(true);
						}


					}

				}
			}

		}

		cara_panel cara_blanca = piezas.getCara(0);

		ArrayList<Integer> arista_blanca = getAristas_blancas(cara_blanca); // ARISTAS BLANCAS QUE COINCIDEN CON LA CARA
																			// BLANCA

		if (arista_blanca.size() > 0) { // COLOCAR AL MENOS UNA ARISTA EN SU SITIO

			System.out.println("COLOCANDO ARISTAS DE LA CARA BLANCA");

			Object[] coincidencias = coincide(arista_blanca, 0);

			boolean coincide = (boolean) coincidencias[0];
			ArrayList<Integer> id_coincide = (ArrayList<Integer>) coincidencias[1];

			while (!coincide) { // VA GIRANDO LA CARA BLANCA HASTA QUE AL MENOS UNA ARISTA COINCIDA CON SUS
								// CARAS

				cara_blanca.girar_derecha(true);
				;

				arista_blanca = getAristas_blancas(cara_blanca);

				coincidencias = coincide(arista_blanca, 0);

				coincide = (boolean) coincidencias[0];

			}

			colocar_desde_cara_amarilla();

			arista_blanca = getAristas_blancas(cara_blanca);
			coincidencias = coincide(arista_blanca, 0);

			id_coincide = (ArrayList<Integer>) coincidencias[1];

			for (int id_arista : arista_blanca) { // BUSCA LAS ARISTAS QUE NO COINCIDEN

				boolean colocado = false;

				for (int ids_coinciden : id_coincide) {

					if (id_arista == ids_coinciden) {
						colocado = true;
					}

				}

				if (!colocado) { // MUEVA LAS ARISTAS QUE NO COINCIDAN A LA CARA AMARILLA

					arista a = piezas.getArista(0, id_arista);

					int cara_otro = -1;

					if (a.id1() != id_arista) {
						cara_otro = a.cara1();
					} else {
						cara_otro = a.cara2();
					}

					cara_panel cara_girar = piezas.getCara(cara_otro);

					cara_girar.girar_derecha(true);
					cara_girar.girar_derecha(true);
					;
				}

			}

		}

		colocar_desde_cara_amarilla();

		boolean todas_colocadas = false;

		while (!todas_colocadas) {

			todas_colocadas = true;

			for (int i = 1; i < 5; i++) { // BUSCARA Y COLOCARA LAS ARISTAS BLANCAS DE LAS 4 CARAS LATERALES

				cara_panel cara_color = piezas.getCara(i);

				cara_panel cara_amarilla = piezas.getCara(5);

				arista_blanca = getAristas_blancas(cara_color);

				System.out.println("Cara: " + i + " num: " + arista_blanca.size());

				for (int id : arista_blanca) {

					System.out.println("	- id: " + id);

				}

				if (arista_blanca.size() > 0) {

					todas_colocadas = false;

					int cara = (i - 1);
					if (cara == 0)
						cara = 4;

					int id = arista_blanca.get(0);

					if (id == 1) { // SI LA ARISTA ESTA EN LA LINIA SUPERIOR

						colocar_arista_blanca(i);

					} else if (id == 7) { // SI ESTA EN LA INFERIOR

						System.out.println("POSICIONANDO...");

						cara_color.girar_derecha(true);
						cara_color.girar_derecha(true);


						cara_amarilla.girar_derecha(true);


						cara_color.girar_derecha(true);
						cara_color.girar_derecha(true);


						colocar_arista_blanca(cara);

					} else { // SI ESTA EN UN LATERAL

						System.out.println("POSICIONANDO...");

						if (id == 3) {

							cara_color.girar_derecha(true);
	

							cara_amarilla.girar_derecha(true);
	

							cara_color.girar_izquierda(true);
	

						} else if (id == 5) {

							cara_color.girar_izquierda(true);
	

							cara_amarilla.girar_derecha(true);
	

							cara_color.girar_derecha(true);
	

						}

						colocar_arista_blanca(cara);

					}

				}
			}
		}

		colocar_desde_cara_amarilla();

	}

	private ArrayList<Integer> getAristas_blancas(cara_panel cara_blanca) { // DEVELVE EL ID DE LAS ARISTAS BLANCAS QUE
																			// SE ENCUENTREN EN LA CARA

		ArrayList<Integer> arista_blanca = new ArrayList<Integer>();

		for (int i = 1; i < 9; i += 2) {

			Color c = cara_blanca.getColor(i);

			if (piezas.getID_Color(c) == 0)
				arista_blanca.add(i);

		}

		return arista_blanca;

	}

	private Object[] coincide(ArrayList<Integer> arista_blanca, int cara) { // COMPRUEBA QUE AL MENOS UNA ARISTA
																			// COINCIDA CON SUS CARA Y DEVUELVE LOS IDS
																			// DE LAS CARAS QUE COINCIDAN

		boolean coincide = false;
		ArrayList<Integer> ids_coinciden = new ArrayList<Integer>();

		for (int id : arista_blanca) {

			arista a = piezas.getArista(cara, id);

			if (a.id1() != id) {

				if (a.cara1() == piezas.getID_Color(a.color1())) {
					coincide = true;
					ids_coinciden.add(id);
				}

			} else {

				if (a.cara2() == piezas.getID_Color(a.color2())) {
					coincide = true;
					ids_coinciden.add(id);
				}

			}

		}

		Object[] coincide_array = { coincide, ids_coinciden };

		return coincide_array;

	}

	@SuppressWarnings("unchecked")
	private void colocar_desde_cara_amarilla() {

		System.out.println("COLOCAR DESDE CARA AMARILLA");

		ArrayList<Integer> arista_blanca;

		cara_panel cara_amarilla = piezas.getCara(5);

		arista_blanca = getAristas_blancas(cara_amarilla);

		if (arista_blanca.size() > 0) {

			Object[] coincidencias = coincide(arista_blanca, 5);

			ArrayList<Integer> id_coincide = (ArrayList<Integer>) coincidencias[1];

			int colocadas = 0;

			while (colocadas < arista_blanca.size()) {

				cara_amarilla.girar_derecha(true);
				;

				arista_blanca = getAristas_blancas(cara_amarilla);

				coincidencias = coincide(arista_blanca, 5);

				if (((boolean) coincidencias[0])) { // CUANDO UNA ARISTA COINCIDE CON SUS CARAS GIRA LA CARA PARA
													// BAJARLA A LA CARA BLANCA

					id_coincide = (ArrayList<Integer>) coincidencias[1];

					for (int ids : id_coincide) {

						arista a = piezas.getArista(5, ids);

						int cara_girar = -1;

						if (a.id1() != ids) {
							cara_girar = a.cara1();
						} else {
							cara_girar = a.cara2();
						}

						piezas.getCara(cara_girar).girar_derecha(true);
						piezas.getCara(cara_girar).girar_derecha(true);


						colocadas++;

					}

				}

			}

		}

	}

	public void colocar_arista_blanca(int cara) { // COLOCARA EN SU POSICION LAS ARISTAS BLANCAS EN LAS QUE EL COLOR QUE
													// NO SE EL BLANCO SE ENCUENTRE EN LA CARA AMARILLA

		System.out.println("COLOCANDO ARISTA");

		cara_panel cara_amarilla = piezas.getCara(5);

		arista a = piezas.getArista(cara, 1);

		int color = piezas.getID_Color(a.color1());

		if (piezas.getID_Color(a.color1()) == 0)
			color = piezas.getID_Color(a.color2());

		System.out.println("Color: " + color);

		int IDcara_destino = (color + 1); // TENDRA QUE MOVER LA CARA AMARILLA HASTA QUE LA ARISTA SE ENCUENTRE EN LA
											// CARA DERECHA A LA QUE LE CORRESPONDE

		if (IDcara_destino == 5)
			IDcara_destino = 1;

		System.out.println("Cara destino: " + IDcara_destino);
		System.out.println("Posicionada en cara: " + cara);

		boolean posicionada = false;

		if (cara == IDcara_destino)
			posicionada = true;

		while (!posicionada) {

			System.out.println("POSICIONANDO...");

			cara_amarilla.girar_derecha(true);
			;

			int cara_posicionada = a.cara1() - 1;

			if (a.cara1() == 5)
				cara_posicionada = a.cara2() - 1;

			if (cara_posicionada == 0)
				cara_posicionada = 4;

			System.out.println("Cara posicionada: " + cara_posicionada);

			a = piezas.getArista(cara_posicionada, 1);

			if (piezas.getID_Color(a.color1()) == 0)
				cara_posicionada = a.cara2();

			if (cara_posicionada == IDcara_destino)
				posicionada = true;

		}

		System.out.println("POSICIONADA");

		System.out.println("COLOCANDO...");

		cara_panel cara_destino = piezas.getCara(IDcara_destino);

		cara_destino.girar_izquierda(true);
		;

		cara_panel cara_final = piezas.getCara(color);

		cara_final.girar_derecha(true);
		;

		cara_destino.girar_derecha(true);
		;

		System.out.println("COLOCADA");

	}

	public void esquinas_blancas() {

		boolean esquinas_colocadas = false;

		while (!esquinas_colocadas) {

			esquinas_colocadas = true;

			for (int i = 1; i < 5; i++) {

				for (int id = 6; id < 9; id += 2) { // LAS DOS ESQUINAS INFERIORES

					esquina e = piezas.getEsquinas(i, id);

					if (piezas.getID_Color(e.color1()) == 0 || piezas.getID_Color(e.color2()) == 0
							|| piezas.getID_Color(e.color3()) == 0) { // SI UNA DE LOS COLORES DE LA ESQUINA ES BLANCA

						System.out.println("Cara: " + i + " esquina: " + id + " tiene color blanco");

						cara_panel cara_color = piezas.getCara(i);
						cara_panel cara_amarilla = piezas.getCara(5);

						int cara_lado;

						if (id == 6) {
							cara_lado = (i - 1);
							if (cara_lado == 0)
								cara_lado = 4;
						} else {
							cara_lado = (i + 1);
							if (cara_lado == 5)
								cara_lado = 1;
						}

						if ((piezas.getID_Color(e.color1()) == 0 && e.cara1() == 0)
								|| (piezas.getID_Color(e.color2()) == 0 && e.cara2() == 0)
								|| (piezas.getID_Color(e.color3()) == 0 && e.cara3() == 0)) { // Color blanco en cara
																								// amarila

							System.out.println("	- Color blanco en cara blanca");

							int color1, cara1;

							if (piezas.getID_Color(e.color1()) == 0) {

								color1 = piezas.getID_Color(e.color2());
								cara1 = e.cara2();

							} else if (piezas.getID_Color(e.color2()) == 0) {

								color1 = piezas.getID_Color(e.color1());
								cara1 = e.cara1();

							} else {

								color1 = piezas.getID_Color(e.color1());
								cara1 = e.cara1();
							}

							if (color1 != cara1) {

								cara_color.girar_izquierda(true);
		

								cara_amarilla.girar_derecha(true);
		

								cara_color.girar_derecha(true);
		

							} else {
								System.out.println("	- Bien colocada");
							}

						} else if ((piezas.getID_Color(e.color1()) == 0 && e.cara1() == i)
								|| (piezas.getID_Color(e.color2()) == 0 && e.cara2() == i)
								|| (piezas.getID_Color(e.color3()) == 0 && e.cara3() == i)) { // Color blanco en la cara
																								// 'i'

							System.out.println("	- Color blanco en la cara: " + i);

							cara_color.girar_izquierda(true);
	

							cara_amarilla.girar_izquierda(true);
	

							cara_color.girar_derecha(true);
	

						} else { // Color blanco en la cara de la izquierda

							System.out.println("	- Color blanco en la cara de al lado");

							cara_color = piezas.getCara(cara_lado);

							cara_color.girar_derecha(true);
	

							cara_amarilla.girar_derecha(true);
	

							cara_color.girar_izquierda(true);
	

						}

					}

				}

				for (int id = 0; id < 3; id += 2) { // LAS DOS ESQUINAS SUPERIORES

					esquina e = piezas.getEsquinas(i, id);

					if (piezas.getID_Color(e.color1()) == 0 || piezas.getID_Color(e.color2()) == 0
							|| piezas.getID_Color(e.color3()) == 0) {

						esquinas_colocadas = false;

						cara_panel cara_amarilla = piezas.getCara(5);

						int color1, color2;
						int cara1, cara2;

						if (piezas.getID_Color(e.color1()) == 0) {

							color1 = piezas.getID_Color(e.color2());
							color2 = piezas.getID_Color(e.color3());

							cara1 = e.cara2();
							cara2 = e.cara3();

							if (cara1 == 5)
								cara1 = e.cara1();
							if (cara2 == 5)
								cara2 = e.cara1();

						} else if (piezas.getID_Color(e.color2()) == 0) {

							color1 = piezas.getID_Color(e.color1());
							color2 = piezas.getID_Color(e.color3());

							cara1 = e.cara1();
							cara2 = e.cara3();

							if (cara1 == 5)
								cara1 = e.cara2();
							if (cara2 == 5)
								cara2 = e.cara2();

						} else {

							color1 = piezas.getID_Color(e.color1());
							color2 = piezas.getID_Color(e.color2());

							cara1 = e.cara1();
							cara2 = e.cara2();

							if (cara1 == 5)
								cara1 = e.cara3();
							if (cara2 == 5)
								cara2 = e.cara3();

						}

						System.out.println("Cara: " + i + " esquina: " + id + " tiene color blanco - color1: " + color1
								+ " cara1: " + cara1 + " - color2: " + color2 + " cara2: " + cara2);

						boolean posicion_correcta = false;

						int cara_posicionada = i;
						int cara_lado = -1;

						if (id == 0) {

							cara_lado = (cara_posicionada - 1);

						} else {

							cara_lado = (cara_posicionada + 1);

						}

						if ((color1 == cara_posicionada && color2 == cara_lado)
								|| (color1 == cara_lado && color2 == cara_posicionada))
							posicion_correcta = true;

						while (!posicion_correcta) {

							cara_amarilla.girar_derecha(true);
	

							cara_posicionada--;
							cara1--;
							cara2--;

							if (cara_posicionada == 0)
								cara_posicionada = 4;
							if (cara1 == 0)
								cara1 = 4;
							if (cara2 == 0)
								cara2 = 4;

							if (id == 0) {

								cara_lado = (cara_posicionada - 1);

								if (cara_lado == 0)
									cara_lado = 4;

							} else {

								cara_lado = (cara_posicionada + 1);

								if (cara_lado == 5)
									cara_lado = 1;
							}

							System.out.println("Cara posicionada: " + cara_posicionada + " Cara lado: " + cara_lado
									+ " Cara1: " + cara1 + " Cara2: " + cara2);

							if ((color1 == cara_posicionada && color2 == cara_lado)
									|| (color1 == cara_lado && color2 == cara_posicionada))
								posicion_correcta = true; // SI LA ESQUINA COINCIDE CON SUS CARAS

						}

						System.out.println("ESQUINA POSICIONADA");

						esquina ep = piezas.getEsquinas(cara_posicionada, id);

						cara_panel cara_color;

						cara_color = piezas.getCara(cara_posicionada);

						System.out.println();

						System.out.println("EP --> Color1: " + piezas.getID_Color(ep.color1()) + " Cara1: " + ep.cara1()
								+ " Color2: " + piezas.getID_Color(ep.color2()) + " Cara2: " + ep.cara2() + "Color3: "
								+ piezas.getID_Color(ep.color3()) + " Cara3: " + ep.cara3());

						if ((piezas.getID_Color(ep.color1()) == 0 && ep.cara1() == 5)
								|| (piezas.getID_Color(ep.color2()) == 0 && ep.cara2() == 5)
								|| (piezas.getID_Color(ep.color3()) == 0 && ep.cara3() == 5)) { // Color blanco en cara
																								// amarila

							System.out.println("	- Color blanco en cara amarilla");

							if (id != 0) {

								cara_color = piezas.getCara(cara_lado);

							}

							cara_color.girar_derecha(true);
	

							cara_amarilla.girar_derecha(true);
	

							cara_amarilla.girar_derecha(true);
	

							cara_color.girar_izquierda(true);
	

							cara_amarilla.girar_izquierda(true);
	

							cara_color.girar_derecha(true);
	

							cara_amarilla.girar_derecha(true);
	

							cara_color.girar_izquierda(true);
	

						} else if ((piezas.getID_Color(ep.color1()) == 0 && ep.cara1() == cara_posicionada)
								|| (piezas.getID_Color(ep.color2()) == 0 && ep.cara2() == cara_posicionada)
								|| (piezas.getID_Color(ep.color3()) == 0 && ep.cara3() == cara_posicionada)) { // Color
																												// blanco
																												// en la
																												// cara
																												// 'i'

							System.out.println("	- Color blanco en la cara: " + i);

							if (id == 0) {

								cara_color.girar_derecha(true);
		

								cara_amarilla.girar_derecha(true);
		

								cara_color.girar_izquierda(true);
		

							} else {

								cara_color.girar_izquierda(true);
		

								cara_amarilla.girar_izquierda(true);
		

								cara_color.girar_derecha(true);
		

							}

						} else { // Color blanco en la cara de al lado

							System.out.println("	- Color blanco en la cara de al lado");

							cara_color = piezas.getCara(cara_lado);

							if (id == 0) {

								cara_color.girar_izquierda(true);
		

								cara_amarilla.girar_izquierda(true);
		

								cara_color.girar_derecha(true);
		

							} else {

								cara_color.girar_derecha(true);
		

								cara_amarilla.girar_derecha(true);
		

								cara_color.girar_izquierda(true);
		

							}

						}

					}

				}

			}
		}
	}

	public void aristas_laterales() {

		cara_panel cara_amarilla = piezas.getCara(5);

		colocar_aristas_color();

		boolean aristas_colocadas = false;

		while (!aristas_colocadas) {

			aristas_colocadas = true;

			for (int i = 1; i < 5; i++) {

				cara_panel cara_color = piezas.getCara(i);

				arista a = piezas.getArista(i, 5);

				boolean colocada = false;

				if (a.cara1() == piezas.getID_Color(a.color1()) && a.cara2() == piezas.getID_Color(a.color2())) {

					colocada = true;

				} else if (a.cara2() == piezas.getID_Color(a.color2()) && a.cara1() == piezas.getID_Color(a.color1())) {

					colocada = true;

				}

				System.out.println("Cara: " + i + " id: 7 Colo1: " + piezas.getID_Color(a.color1()) + " Cara1: "
						+ a.cara1() + " Color2: " + piezas.getID_Color(a.color2()) + " Cara2: " + a.cara2());

				if (!colocada) {

					aristas_colocadas = false;

					int id_cara_derecha = (i + 1);

					if (id_cara_derecha == 5)
						id_cara_derecha = 1;

					System.out.println("Cara: " + i + " id: 7 arista mal colocada. Cara derecha: " + id_cara_derecha);

					cara_panel cara_derecha = piezas.getCara(id_cara_derecha);

					cara_derecha.girar_derecha(true);

					cara_amarilla.girar_derecha(true);
					;

					cara_derecha.girar_izquierda(true);
					;

					cara_amarilla.girar_izquierda(true);
					;

					cara_color.girar_izquierda(true);
					;

					cara_amarilla.girar_izquierda(true);
					;

					cara_color.girar_derecha(true);
					;

					colocar_aristas_color();

				}

			}
		}
	}

	public void colocar_aristas_color() {

		cara_panel cara_amarilla = piezas.getCara(5);

		boolean aristas_superiores = false;

		while (!aristas_superiores) {

			aristas_superiores = true;

			for (int id = 1; id < 8; id += 2) {

				int color_cara_amarilla = piezas.getID_Color(cara_amarilla.getColor(id));

				int color_cara_lateral = -1;
				int id_cara_lateral = -1;

				boolean arista_colocar = false;

				if (color_cara_amarilla != 5) {

					arista a = piezas.getArista(5, id);

					if (piezas.getID_Color(a.color1()) == color_cara_amarilla && piezas.getID_Color(a.color2()) != 5) {

						arista_colocar = true;
						color_cara_lateral = piezas.getID_Color(a.color2());
						id_cara_lateral = a.cara2();

					} else if (piezas.getID_Color(a.color2()) == color_cara_amarilla
							&& piezas.getID_Color(a.color1()) != 5) {

						arista_colocar = true;
						color_cara_lateral = piezas.getID_Color(a.color1());
						id_cara_lateral = a.cara1();

					}

				}

				if (arista_colocar) {

					aristas_superiores = false;

					System.out.println("Cara amarilla, id: " + id + " arista con color: " + color_cara_amarilla
							+ " y color: " + color_cara_lateral + " en cara: " + id_cara_lateral);

					boolean arista_posicionada = false;

					if (color_cara_lateral == id_cara_lateral)
						arista_posicionada = true;

					while (!arista_posicionada) {

						cara_amarilla.girar_derecha(true);


						id_cara_lateral--;

						if (id_cara_lateral == 0)
							id_cara_lateral = 4;

						System.out.println("Arista posicionada en cara: " + id_cara_lateral);

						if (color_cara_lateral == id_cara_lateral)
							arista_posicionada = true;

					}

					System.out.println("ARISTA POSICIONADA");

					cara_panel cara_lateral = piezas.getCara(id_cara_lateral);

					int id_cara_derecha = (id_cara_lateral + 1);
					int id_cara_izquierda = (id_cara_lateral - 1);

					if (id_cara_derecha == 5)
						id_cara_derecha = 1;
					if (id_cara_izquierda == 0)
						id_cara_izquierda = 4;

					if (color_cara_amarilla == id_cara_derecha) {

						System.out.println("Cara correspondiente en el lado derecho");

						cara_panel cara_derecha = piezas.getCara(id_cara_derecha);

						cara_amarilla.girar_derecha(true);


						cara_derecha.girar_derecha(true);


						cara_amarilla.girar_izquierda(true);


						cara_derecha.girar_izquierda(true);


						cara_amarilla.girar_izquierda(true);


						cara_lateral.girar_izquierda(true);


						cara_amarilla.girar_derecha(true);


						cara_lateral.girar_derecha(true);


					} else if (color_cara_amarilla == id_cara_izquierda) {

						System.out.println("Cara correspondiente en el lado izquierdo");

						cara_panel cara_izquierda = piezas.getCara(id_cara_izquierda);

						cara_amarilla.girar_izquierda(true);


						cara_izquierda.girar_izquierda(true);


						cara_amarilla.girar_derecha(true);


						cara_izquierda.girar_derecha(true);


						cara_amarilla.girar_derecha(true);


						cara_lateral.girar_derecha(true);


						cara_amarilla.girar_izquierda(true);


						cara_lateral.girar_izquierda(true);


					}

				}

			}
		}

	}

	public void cruz_amarilla() {

		cara_panel cara_amarilla = piezas.getCara(5);

		boolean cruz_amarilla = false;

		while (!cruz_amarilla) {

			int num_aristas_amarillas = 0;

			for (int i = 1; i < 8; i += 2) {

				if (piezas.getID_Color(cara_amarilla.getColor(i)) == 5)
					num_aristas_amarillas++;

			}

			if (num_aristas_amarillas == 0) {

				paso_cruz_amarilla();

			} else if (num_aristas_amarillas == 2) {

				int num_giros = 0;

				if (piezas.getID_Color(cara_amarilla.getColor(1)) == 5
						&& piezas.getID_Color(cara_amarilla.getColor(5)) == 5) {
					num_giros = 1;
				}

				if (piezas.getID_Color(cara_amarilla.getColor(7)) == 5
						&& piezas.getID_Color(cara_amarilla.getColor(3)) == 5) {
					num_giros = 3;
				}
				if (piezas.getID_Color(cara_amarilla.getColor(3)) == 5
						&& piezas.getID_Color(cara_amarilla.getColor(1)) == 5) {
					num_giros = 2;
				}

				if (piezas.getID_Color(cara_amarilla.getColor(1)) == 5
						&& piezas.getID_Color(cara_amarilla.getColor(7)) == 5) {
					num_giros = 1;
				}

				for (int i = 0; i < num_giros; i++) {

					cara_amarilla.girar_derecha(true);

				}

				;

				paso_cruz_amarilla();

			} else if (num_aristas_amarillas == 4) {

				cruz_amarilla = true;

			}
		}
	}

	public void paso_cruz_amarilla() {

		cara_panel cara_amarilla = piezas.getCara(5);
		cara_panel cara_naranja = piezas.getCara(2);
		cara_panel cara_azul = piezas.getCara(3);

		cara_naranja.girar_derecha(true);
		;

		cara_azul.girar_derecha(true);
		;

		cara_amarilla.girar_derecha(true);
		;

		cara_azul.girar_izquierda(true);
		;

		cara_amarilla.girar_izquierda(true);
		;

		cara_naranja.girar_izquierda(true);
		;

	}

	public void aristas_superiores() {

		cara_panel cara_amarilla = piezas.getCara(5);

		boolean aristas_colocadas = false;

		while (!aristas_colocadas) {

			int num_aristas_colocadas = getNum_aristas_superiores();

			while (num_aristas_colocadas < 2) {

				cara_amarilla.girar_derecha(true);
				;

				num_aristas_colocadas = getNum_aristas_superiores();

			}

			if (num_aristas_colocadas == 2) {

				boolean caras_opuestas = false;
				boolean caras_continuas = false;

				int cara1 = 0;
				int cara2 = 0;

				while (!caras_opuestas && !caras_continuas) {

					cara_amarilla.girar_derecha(true);
					;

					for (int i = 1; i < 5; i++) {

						cara_panel cara_color = piezas.getCara(i);

						int id_cara_lado = (i + 1);

						if (id_cara_lado == 5)
							id_cara_lado = 1;

						cara_panel cara_lado = piezas.getCara(id_cara_lado);

						int id_cara_opuesto = (id_cara_lado + 1);

						if (id_cara_opuesto == 5)
							id_cara_opuesto = 1;

						cara_panel cara_opuesta = piezas.getCara(id_cara_opuesto);

						if (piezas.getID_Color(cara_color.getColor(1)) == i
								&& piezas.getID_Color(cara_lado.getColor(1)) == id_cara_lado) {

							caras_continuas = true;

							cara1 = i;
							cara2 = id_cara_lado;

							break;

						} else if (piezas.getID_Color(cara_color.getColor(1)) == i
								&& piezas.getID_Color(cara_opuesta.getColor(1)) == id_cara_opuesto) {

							caras_opuestas = true;

							cara1 = i;
							cara2 = id_cara_lado;

							break;
						}

					}
				}

				for (int i = 1; i < 5; i++) {

					if (i != cara1 && i != cara2) {

						int id_cara_lado = (i + 1);

						if (id_cara_lado == 5)
							id_cara_lado = 1;

						if (id_cara_lado == cara1 || id_cara_lado == cara2) {

							cara_panel cara_lado = piezas.getCara(id_cara_lado);

							cara_lado.girar_derecha(true);
	

							cara_amarilla.girar_derecha(true);
	

							cara_lado.girar_izquierda(true);
	

							cara_amarilla.girar_derecha(true);
	

							cara_lado.girar_derecha(true);
	

							cara_amarilla.girar_derecha(true);
	

							cara_amarilla.girar_derecha(true);
	

							cara_lado.girar_izquierda(true);
	

						}

					}

				}

			} else if (num_aristas_colocadas == 4) {
				aristas_colocadas = true;
			}
		}
	}

	public int getNum_aristas_superiores() {

		int num_aristas_colocadas = 0;

		for (int i = 1; i < 5; i++) {

			cara_panel cara_color = piezas.getCara(i);

			if (piezas.getID_Color(cara_color.getColor(1)) == i)
				num_aristas_colocadas++;

		}

		return num_aristas_colocadas;

	}

	public void permutar_esquinas() {

		boolean colocadas = false;

		while (!colocadas) {

			colocadas = true;

			int cara_correcta = 2;

			for (int i = 1; i < 5; i++) {

				esquina e = piezas.getEsquinas(i, 2);

				int color1 = piezas.getID_Color(e.color1());
				int color2 = piezas.getID_Color(e.color2());
				int color3 = piezas.getID_Color(e.color3());

				int cara1 = e.cara1();
				int cara2 = e.cara2();
				int cara3 = e.cara3();

				if ((color1 == cara1 || color1 == cara2 || color1 == cara3)
						&& (color2 == cara1 || color2 == cara2 || color2 == cara3)
						&& (color3 == cara1 || color3 == cara2 || color3 == cara3)) {

					cara_correcta = i;

				} else {
					colocadas = false;
				}

			}

			int id_cara_derecha = (cara_correcta + 1);
			if (id_cara_derecha == 5)
				id_cara_derecha = 1;

			int id_cara_izquierda = (cara_correcta - 1);
			if (id_cara_izquierda == 0)
				id_cara_izquierda = 4;

			cara_panel cara_amarilla = piezas.getCara(5);
			cara_panel cara_derecha = piezas.getCara(id_cara_derecha);
			cara_panel cara_izquierda = piezas.getCara(id_cara_izquierda);

			while (!colocadas) {

				cara_amarilla.girar_derecha(true);

				cara_derecha.girar_derecha(true);

				cara_amarilla.girar_izquierda(true);

				cara_izquierda.girar_izquierda(true);

				cara_amarilla.girar_derecha(true);

				cara_derecha.girar_izquierda(true);

				cara_amarilla.girar_izquierda(true);

				cara_izquierda.girar_derecha(true);

				esquina e = piezas.getEsquinas(cara_correcta, 0);

				int color1 = piezas.getID_Color(e.color1());
				int color2 = piezas.getID_Color(e.color2());
				int color3 = piezas.getID_Color(e.color3());

				int cara1 = e.cara1();
				int cara2 = e.cara2();
				int cara3 = e.cara3();

				if ((color1 == cara1 || color1 == cara2 || color1 == cara3)
						&& (color2 == cara1 || color2 == cara2 || color2 == cara3)
						&& (color3 == cara1 || color3 == cara2 || color3 == cara3)) {

					colocadas = true;

				} else {
					colocadas = false;
				}

			}
		}
	}

	public void girar_esquinas() {

		int id_cara = -1;

		for (int i = 1; i < 5; i++) {

			cara_panel cara_color = piezas.getCara(i);

			if (piezas.getID_Color(cara_color.getColor(2)) != i) {
				id_cara = i;
				break;
			}

		}

		System.out.println("Cara: " + id_cara + " esquina: 2 bien situada");

		if (id_cara != -1) {

			id_cara = 2;

			int id_cara_esquina = id_cara;

			int id_cara_derecha = (id_cara + 1);
			if (id_cara_derecha == 5)
				id_cara_derecha = 1;

			int id_cara_esquina2 = id_cara_derecha;

			for (int i = 0; i < 5; i++) {

				cara_panel cara_color = piezas.getCara(id_cara);
				cara_panel cara_derecha = piezas.getCara(id_cara_derecha);
				cara_panel cara_amarilla = piezas.getCara(5);
				cara_panel cara_blanca = piezas.getCara(0);

				System.out.println("Cara: " + id_cara + " Cara derecha: " + id_cara_derecha);

				if (piezas.getID_Color(cara_color.getColor(2)) != id_cara_esquina) {

					boolean girada = false;

					while (!girada) {

						cara_derecha.girar_izquierda(true);


						cara_blanca.girar_izquierda(true);


						cara_derecha.girar_derecha(true);


						cara_blanca.girar_derecha(true);


						cara_amarilla = piezas.getCara(5);

						esquina e = piezas.getEsquinas(id_cara, 2);

						System.out.println("Color1: " + piezas.getID_Color(e.color1()) + " Cara1: " + e.cara1()
								+ " Colo2: " + piezas.getID_Color(e.color2()) + " Cara2: " + id_cara_esquina
								+ " Color3: " + piezas.getID_Color(e.color3()) + " Cara3: " + id_cara_esquina2);

						if (e.cara1() == piezas.getID_Color(e.color1())
								&& id_cara_esquina == piezas.getID_Color(e.color2())
								&& id_cara_esquina2 == piezas.getID_Color(e.color3())) {
							girada = true;

							System.out.println("Girada");

						}

					}

				}

				cara_amarilla = piezas.getCara(5);

				cara_amarilla.girar_izquierda(true);
				

				id_cara_esquina--;
				id_cara_esquina2--;
				if (id_cara_esquina == 0)
					id_cara_esquina = 4;
				if (id_cara_esquina2 == 0)
					id_cara_esquina2 = 4;

			}
		}

	}

	public void finalizar() {

		cara_panel cara_amarilla = piezas.getCara(5);

		cara_panel cara_color = piezas.getCara(2);

		if (piezas.getID_Color(cara_color.getColor(1)) != 2) {

			cara_amarilla.girar_izquierda(true);

			finalizar();

		}

	}

}
