package kz.epam.parser;

import kz.epam.music.Track;
import kz.epam.music.Tracklist;
import kz.epam.music.tracks.MozartTrack;
import kz.epam.music.tracks.NickelbackTrack;
import kz.epam.music.tracks.QueenTrack;
import kz.epam.validator.ValidatorXSD;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class DomParser {
    private static final String ATTRIBUTE_AUTHOR_OF_TRACK = "author";
    private static final String ATTRIBUTE_QUEEN = "Queen";
    private static final String ATTRIBUTE_MOZART = "Mozart";
    private static final String ATTRIBUTE_NICKELBACK = "Nickelback";
    private static final String ELEMENT_TRACK = "track";
    private static final String ELEMENT_NAME = "name";
    private static final String ELEMENT_DURATION = "duration";
    private static final String ELEMENT_STYLE = "style";
    private static final Integer ITEM_INDEX = 0;
    private ArrayList<Track> tracks = new ArrayList<>();
    private Track track;
    private Tracklist tracklist= new Tracklist();
    private Element e = null;

    public Tracklist parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(ValidatorXSD.FILE_NAME);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(ELEMENT_TRACK);
            for(int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    e = (Element) node;
                    switch (e.getAttribute(ATTRIBUTE_AUTHOR_OF_TRACK)) {
                        case ATTRIBUTE_QUEEN :
                            track = new QueenTrack();
                            getElements(track);
                            break;
                        case ATTRIBUTE_MOZART :
                            track = new MozartTrack();
                            getElements(track);
                            break;
                        case ATTRIBUTE_NICKELBACK :
                            track = new NickelbackTrack();
                            getElements(track);
                            break;
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tracklist.add(tracks);
        return tracklist;
    }

    public void getElements(Track track) {
        track.setName(e.getElementsByTagName(ELEMENT_NAME).item(ITEM_INDEX).getTextContent());
        track.setDuration(Double.parseDouble(e.getElementsByTagName(ELEMENT_DURATION).item(ITEM_INDEX).getTextContent()));
        track.setStyle(e.getElementsByTagName(ELEMENT_STYLE).item(ITEM_INDEX).getTextContent());
        tracks.add(track);
    }
}
