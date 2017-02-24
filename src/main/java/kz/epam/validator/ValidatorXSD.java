package kz.epam.validator;

import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;


public class ValidatorXSD {
    public static final String FILE_NAME = "src/main/resources/tracklist.xml";
    public static final String SCHEMA_NAME = "src/main/resources/tracklist.xsd";
    private static ValidatorXSD validatorXSD;

    private ValidatorXSD(){
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;

        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(SCHEMA_NAME);

        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(FILE_NAME);
            validator.validate(source);
            System.out.println(FILE_NAME + " is valid.");
        } catch (SAXException | IOException e) {
            System.err.print("validation " + FILE_NAME + " is not valid because " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static ValidatorXSD validate() {
        if(validatorXSD == null) {
         validatorXSD = new ValidatorXSD();
        }
        return validatorXSD;
    }
}
