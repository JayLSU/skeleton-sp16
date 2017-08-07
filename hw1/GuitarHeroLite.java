/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHeroLite {

    // this part is for guitar
    /**************************************************************************/
    /*private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);*/
    /**************************************************************************/

    // this part is for keyboard
    /**************************************************************************/
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    /**************************************************************************/

    public static void main(String[] args) {
        // this part is for guitar
        /**************************************************************************/
        /* create two guitar strings, for concert A and C */
        /*synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);*/
        /**************************************************************************/

        // this part is for keyboard
        /**************************************************************************/
        /* create 37 keyboard array and initialize each with corresponding frequency*/
        synthesizer.GuitarString[] Pianokeyboard = new synthesizer.GuitarString[37];
        for (int i =0; i < 37; i++){
            double freq = 440 * Math.pow(2,(i - 24)/12);
            Pianokeyboard[i] = new synthesizer.GuitarString(freq);
        }
        /**************************************************************************/


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                // this part is for guitar
                /**************************************************************************/
                /*if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                }*/
                /**************************************************************************/


                // this part is for keyboard
                /**************************************************************************/
                if (keyboard.contains( Character.toString(key))) { // make sure no crash on unexpected key press
                    // keyboard play
                    int keyInd = keyboard.indexOf(key);
                    double fre = 440 * Math.pow(2, (keyInd - 24) / 12);
                    Pianokeyboard[keyInd] = new synthesizer.GuitarString(fre);
                    Pianokeyboard[keyInd].pluck();
                }
                /**************************************************************************/
            }

        /* compute the superposition of samples */
            // this is for guitar
            /**************************************************************************/
            /* double sample = stringA.sample() + stringC.sample(); */
            /**************************************************************************/

            // this part is for keyboard
            /**************************************************************************/
            double sample = 0.0;
            for (int j = 0; j < 37; j++){
                sample += Pianokeyboard[j].sample();
            }
            /**************************************************************************/


        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */

            // this part is for guitar
            /**************************************************************************/
            /*stringA.tic();
            stringC.tic();*/
            /**************************************************************************/

            // this part is for keyboard
            /**************************************************************************/
            for (int i =0; i < 37; i++){
                Pianokeyboard[i].tic();
            }
            /**************************************************************************/
        }
    }
}

