package main;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class GUI {

    public static ArrayList<String> countries = new ArrayList<String>() {
        {
            add("Albania");
            add("Andorra");
            add("Armenia");
            add("Austria");
            add("Azerbaijan");
            add("Belarus");
            add("Belgium");
            add("Bosnia and Herzegovina");
            add("Bulgaria");
            add("Croatia");
            add("Cyprus");
            add("Czechia");
            add("Denmark");
            add("Estonia");
            add("Finland");
            add("France");
            add("Georgia");
            add("Germany");
            add("Greece");
            add("Hungary");
            add("Iceland");
            add("Ireland");
            add("Italy");
            add("Kazakhstan");
            add("Kosovo");
            add("Latvia");
            add("Liechtenstein");
            add("Lithuania");
            add("Luxembourg");
            add("Malta");
            add("Moldova");
            add("Monaco");
            add("Montenegro");
            add("Netherlands");
            add("North Macedonia");
            add("Norway");
            add("Poland");
            add("Portugal");
            add("Romania");
            add("Russia");
            add("San Marino");
            add("Serbia");
            add("Slovakia");
            add("Slovenia");
            add("Spain");
            add("Sweden");
            add("Switzerland");
            add("Turkey");
            add("Ukraine");
            add("United Kingdom");
            add("Vatican City");

            add("Afghanistan");
            add("Bahrain");
            add("Bangladesh");
            add("Bhutan");
            add("Brunei");
            add("Cambodia");
            add("China");
            add("East Timor");
            add("India");
            add("Indonesia");
            add("Iran");
            add("Iraq");
            add("Israel");
            add("Japan");
            add("Jordan");
            add("Kuwait");
            add("Kyrgyzstan");
            add("Laos");
            add("Lebanon");
            add("Malaysia");
            add("Maldives");
            add("Mongolia");
            add("Myanmar");
            add("Nepal");
            add("North Korea");
            add("Oman");
            add("Pakistan");
            add("Palestine");
            add("Philippines");
            add("Qatar");
            add("Saudi Arabia");
            add("Singapore");
            add("South Korea");
            add("Sri Lanka");
            add("Syria");
            add("Taiwan");
            add("Tajikistan");
            add("Thailand");
            add("Turkmenistan");
            add("United Arab Emirates");
            add("Uzbekistan");
            add("Vietnam");
            add("Yemen");

            add("Australia");
            add("Fiji");
            add("Marshall Islands");
            add("Micronesia");
            add("New Zealand");
            add("Palau");
            add("Papua New Guinea");
            add("Samoa");
            add("Solomon Islands");
            add("Vanuatu");
        }
    };

    boolean isLoaded = false;

    public class loadImagesDemo extends SwingWorker<HashMap, HashMap> {
        HashMap<String, BufferedImage> images = new HashMap<>();

        JProgressBar jpb;
        JLabel jl;
        JPanel jf;

        public loadImagesDemo(JProgressBar jpb, JLabel jl, JPanel jFinal){
            this.jpb = jpb;
            this.jl = jl;
            this.jf = jFinal;
        }

        @Override
        protected HashMap doInBackground() {
            for(String country : countries){
                String imageURL = this.getClass().getClassLoader().getResource(country.replaceAll(" ", "_") + ".jpg").getFile();
                BufferedImage myPicture = null;
                try {
                    myPicture = ImageIO.read(new File(imageURL));
                    images.put(country, myPicture);
                    jpb.setValue(countries.indexOf(country) + 1);
                    jl.setText("Loading...(" + countries.indexOf(country) + "/" + countries.size() + ")");
                    System.out.println(country);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            return images;
        }

        @Override
        protected void done() {
            try {
                get();
                jpb.getParent().setVisible(false);
                jf.setVisible(true);
                jf.repaint();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadGUI() throws ExecutionException, InterruptedException {

        JFrame frame = new JFrame();
        frame.setSize(800,650);
        //frame.setLayout(new MigLayout());

        JPanel holder = new JPanel();
        holder.setLayout(new MigLayout("align 50%"));

        JPanel flagsection = new JPanel();
        flagsection.setLayout(new MigLayout("top,center"));

        JPanel selectsection = new JPanel();
        selectsection.setLayout(new MigLayout("bottom,center"));

        JPanel loadingbar = new JPanel();
        loadingbar.setLayout(new MigLayout("align 50% 50%"));

        JProgressBar jpb = new JProgressBar();
        JLabel loadlabel = new JLabel();
        jpb.setIndeterminate(false);
        jpb.setMaximum(countries.size());

        loadingbar.add(jpb, "wrap");
        loadingbar.add(loadlabel,"center");
        frame.add(loadingbar);

        loadImagesDemo y = new loadImagesDemo(jpb, loadlabel, holder);

        y.execute();

        frame.setVisible(true);

        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon());


        JLabel applicationtitle = new JLabel("Eurasia + Oceania Flag Selection Menu");
        applicationtitle.setFont(new Font("",Font.BOLD,28));
        JLabel countryhelp = new JLabel("Select a country from this menu, and the country's flag will show below.");
        JComboBox countrieslist = new JComboBox(countries.toArray());
        countrieslist.setSelectedIndex(0);

        flagsection.add(picture);
        countrieslist.addActionListener(e -> {
            BufferedImage x = null;
            try {
                x = (BufferedImage) y.get().get(countrieslist.getSelectedItem());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            picture.setIcon(new ImageIcon(x));
            picture.repaint();

        });

        selectsection.add(applicationtitle, "center,span");
        selectsection.add(countryhelp);
        selectsection.add(countrieslist);


        holder.add(selectsection,"span");
        holder.add(flagsection);
        frame.add(holder);
        holder.setVisible(false);
        frame.setVisible(true);

    }
}
