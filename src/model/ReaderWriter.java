package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.naming.directory.InvalidAttributesException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReaderWriter {
    private PrintWriter writer;
    private BufferedReader reader;
    
   public ReaderWriter(Socket socket) throws IOException {
        this.writer = new PrintWriter(socket.getOutputStream(), true);
        this.reader = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
    }


    public PrintWriter getWriter() throws InvalidAttributesException {
        if (writer != null) {
            return writer;
        } else {
            throw new InvalidAttributesException("Instance null");
        }
    }

    public BufferedReader getReader() throws InvalidAttributesException {
        if (reader != null) {
            return reader;
        } else {
            throw new InvalidAttributesException("Instance null");
        }
    }

    // public AgentAction read() throws JsonMappingException, JsonProcessingException, IOException {
    //     ObjectMapper mapper = new ObjectMapper();
    //     return mapper.readValue(reader.readLine(), AgentAction.class);
    // }
}
