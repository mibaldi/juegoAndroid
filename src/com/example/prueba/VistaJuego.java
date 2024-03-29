package com.example.prueba;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class VistaJuego extends View {

    // //// ASTEROIDES //////

    private Vector<Grafico> Asteroides; // Vector con los Asteroides

    private int numAsteroides= 1; // N�mero inicial de asteroides

    private int numFragmentos= 3; // Fragmentos en que se divide

 // //// NAVE //////

    private Grafico nave;// Gr�fico de la nave

    private int giroNave; // Incremento de direcci�n

    private float aceleracionNave; // aumento de velocidad

    // Incremento est�ndar de giro y aceleraci�n

    private static final int PASO_GIRO_NAVE = 5;

    private static final float PASO_ACELERACION_NAVE = 0.5f;

    public VistaJuego(Context context, AttributeSet attrs) {

          super(context, attrs);

          Drawable drawableNave, drawableAsteroide, drawableMisil;

          drawableAsteroide = context.getResources().getDrawable(R.drawable.asteroide1);
          drawableNave = context.getResources().getDrawable(R.drawable.nave2);
          Asteroides = new Vector<Grafico>();
          nave = new Grafico(this, drawableNave);
          for (int i = 0; i < numAsteroides; i++) {

                Grafico asteroide = new Grafico(this, drawableAsteroide);

                asteroide.setIncY(Math.random() * 4 - 2);

                asteroide.setIncX(Math.random() * 4 - 2);

                asteroide.setAngulo((int) (Math.random() * 360));

                asteroide.setRotacion((int) (Math.random() * 8 - 4));

                Asteroides.add(asteroide);

          }

    }



    @Override protected void onSizeChanged(int ancho, int alto,
                                                         int ancho_anter, int alto_anter) {

          super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);

          // Una vez que conocemos nuestro ancho y alto.

          for (Grafico asteroide: Asteroides) {

        	  do{

        	      asteroide.setPosX(Math.random()*(ancho-asteroide.getAncho()));

        	      asteroide.setPosY(Math.random()*(alto-asteroide.getAlto()));

        	} while(asteroide.distancia(nave) < (ancho+alto)/5);
        	 
          }
          nave.setPosX(ancho/2);
          nave.setPosY(alto/2);

    }



    @Override protected void onDraw(Canvas canvas) {

          super.onDraw(canvas);

          for (Grafico asteroide: Asteroides) {

              asteroide.dibujaGrafico(canvas);

          }
          nave.dibujaGrafico(canvas);

    }

}