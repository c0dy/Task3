package kz.epam.parser;

import kz.epam.music.Track;
import kz.epam.music.Tracklist;
import kz.epam.music.tracks.MozartTrack;
import kz.epam.music.tracks.NickelbackTrack;
import kz.epam.music.tracks.QueenTrack;
import kz.epam.validator.ValidatorXSD;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class StaxParser {
    private static final String ATTRIBUTE_QUEEN = "Queen";
    private static final String ATTRIBUTE_MOZART = "Mozart";
    private static final String ATTRIBUTE_NICKELBACK = "Nickelback";
    private static final String ELEMENT_TRACK = "track";
    private static final String ELEMENT_NAME = "name";
    private static final String ELEMENT_DURATION = "duration";
    private static final String ELEMENT_STYLE = "style";
    private static final Integer ITEM_INDEX = 0;
    private ArrayList<Track> tracks = new ArrayList<>();
    private Tracklist tracklist = new Tracklist();
    private Track track;
    private String str = "";

    public Tracklist parse() {
        XMLInputFactory factory = XMLInputFactory.newFactory();

        try {
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(ValidatorXSD.FILE_NAME));
            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT :
                        if (reader.getLocalName().equals(ELEMENT_TRACK)) {

                            switch (reader.getAttributeValue(ITEM_INDEX)) {
                                case ATTRIBUTE_QUEEN :
                                    track = new QueenTrack();
                                    break;
                                case ATTRIBUTE_MOZART :
                                    track = new MozartTrack();
                                    break;
                                case ATTRIBUTE_NICKELBACK :
                                    track = new NickelbackTrack();
                                    break;
                            }
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS :
                        str = reader.getText();
                        break;

                    case XMLStreamConstants.END_ELEMENT :

                        switch (reader.getLocalName()) {
                            case ELEMENT_NAME :
                                track.setName(str);
                                break;
                            case ELEMENT_DURATION :
                                track.setDuration(Double.parseDouble(str));
                                break;
                            case ELEMENT_STYLE :
                                track.setStyle(str);
                                break;
                            case ELEMENT_TRACK :
                                tracks.add(track);
                                break;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tracklist.add(tracks);
        return tracklist;
    }
}
