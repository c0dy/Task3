package kz.epam.main;


import kz.epam.music.Track;
import kz.epam.music.Tracklist;
import kz.epam.parser.DomParser;
import kz.epam.parser.SaxParser;
import kz.epam.parser.StaxParser;


public class UI {

    public UI() {
        Tracklist domParser = new DomParser().parse();
        Tracklist saxParser = new SaxParser().parse();
        Tracklist staxParser = new StaxParser().parse();

        System.out.println("\nParser equals: ");
        System.out.println(domParser.equals(saxParser));
        System.out.println(saxParser.equals(staxParser));
        System.out.println(staxParser.equals(domParser));

        show(domParser, "DOMParser");
        show(saxParser, "SAXParser");
        show(staxParser, "StAXParser");

    }

    public void show(Tracklist tracklist, String parserName) {
        System.out.println("\n" +parserName);

        for (Track t : tracklist.getTracklist()) {
            System.out.println("-------------------------");
            System.out.println(t);
        }
        System.out.println("-------------------------\n");
    }
}
