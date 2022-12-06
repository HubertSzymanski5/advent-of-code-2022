package pl.szymhu.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommunicationSystemSignalProcessorTest {

    @Test
    void shouldFindMarkerAfterChar5() {
        var signal = "bvwbjplbgvbhsrlpgdmjqwftvncz";
        assertEquals(5, CommunicationSystemSignalProcessor.getPacketStartIndex(signal));
    }

    @Test
    void shouldFindMarkerAfterChar6() {
        var signal = "nppdvjthqldpwncqszvftbrmjlhg";
        assertEquals(6, CommunicationSystemSignalProcessor.getPacketStartIndex(signal));
    }

    @Test
    void shouldFindMarkerAfterChar10() {
        var signal = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
        assertEquals(10, CommunicationSystemSignalProcessor.getPacketStartIndex(signal));
    }

    @Test
    void shouldFindMarkerAfterChar11() {
        var signal = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
        assertEquals(11, CommunicationSystemSignalProcessor.getPacketStartIndex(signal));
    }

    @Test
    void shouldFindMessageAfterChar19() {
        var signal = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
        assertEquals(19, CommunicationSystemSignalProcessor.getMessageStartIndex(signal));
    }

    @Test
    void shouldFindMessageAfterChar23() {
        var signal = "bvwbjplbgvbhsrlpgdmjqwftvncz";
        assertEquals(23, CommunicationSystemSignalProcessor.getMessageStartIndex(signal));
    }

    @Test
    void shouldFindMessageAfterChar29() {
        var signal = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
        assertEquals(29, CommunicationSystemSignalProcessor.getMessageStartIndex(signal));
    }

    @Test
    void shouldFindMessageAfterChar26() {
        var signal = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
        assertEquals(26, CommunicationSystemSignalProcessor.getMessageStartIndex(signal));
    }

}