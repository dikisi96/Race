package resume;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XMLParser {
    private static Logger fileLogger = LoggerFactory.getLogger("FILE");

    public static Parameters parse(File xmlFile, File schemaFile) {
        Parameters parameters = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Parameters.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaFile);
            unmarshaller.setSchema(schema);
            parameters = (Parameters) unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException | SAXException e) {
            fileLogger.error(e.getMessage());
            e.printStackTrace();
        }
        return parameters;
    }
}