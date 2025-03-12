package org.example;


import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Testing {

    @org.junit.jupiter.api.Test
    public void testDeserializeAll() throws IOException {

        Result result1 = new Result("AED", "ALL", 100 );

        Serialize.serialize(result1, "1");
        Result result2 = new Result("AED", "AED", 50 );

        Serialize.serialize(result2, "2");
        Result result3 = new Result("AED", "CAD", 30 );

        Serialize.serialize(result3, "3");
        Result result4 = new Result("CAD", "ALL", 10230 );

        Serialize.serialize(result4, "4");

        System.out.println(Serialize.deserializeAll());
    }

    @Test
    public void testDeserializeSingle() throws IOException {
        Result result = new Result("AED", "CAD", 60);
        Serialize.serialize(result,"5");

        System.out.println(Serialize.deserializeSingle("history/result5.json"));
    }
}
