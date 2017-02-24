package kz.epam.parser;

import kz.epam.music.Track;
import kz.epam.music.Tracklist;
import kz.epam.music.tracks.MozartTrack;
import kz.epam.music.tracks.NickelbackTrack;
import kz.epam.music.tracks.QueenTrack;
import kz.epam.validator.ValidatorXSD;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;


public class SaxParser extends DefaultHandler {
    private static final String ATTRIBUTE_QUEEN = "Queen";
    private static final String ATTRIBUTE_MOZART = "Mozart";
    private static final String ATTRIBUTE_NICKELBACK = "Nickelback";
    private static final String ELEMENT_TRACK = "track";
    private static final String ELEMENT_NAME = "name";
    private static final String ELEMENT_DURATION = "duration";
    private static final String ELEMENT_STYLE = "style";
    private static ArrayList<Track> tracks = new ArrayList<>();
    private Tracklist tracklist = new Tracklist();
    private StringBuilder stringBuilder;
    private Track track;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case ELEMENT_TRACK :
                switch (attributes.getValue(0).toString()) {
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
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       stringBuilder = new StringBuilder();
       stringBuilder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case ELEMENT_NAME :
                track.setName(stringBuilder.toString());
                break;
            case ELEMENT_DURATION :
                track.setDuration(Double.parseDouble(stringBuilder.toString()));
                break;
            case ELEMENT_STYLE :
                track.setStyle(stringBuilder.toString());
                break;
            case ELEMENT_TRACK :
                tracks.add(track);
                break;
        }
    }

    public Tracklist parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new SaxParser();
            saxParser.parse(ValidatorXSD.FILE_NAME, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        tracklist.add(tracks);
        return tracklist;
    }
}
