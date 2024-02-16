package model.Transfert;

public class MessageBuilder {
    public static Message build(String type, String data)
    {
        return new Message(type, data);
    }

    public static Message buildFromString(String str)
    {
        int pos = str.indexOf(" ");
        String type = str.substring(0, pos);
        String data = str.substring(pos + 1);
        //System.out.println(String.format("\"%s\" \"%s\"", type, data));
        return new Message(type, data);
    }
}
