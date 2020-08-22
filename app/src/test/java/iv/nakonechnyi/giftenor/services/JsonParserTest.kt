package iv.nakonechnyi.giftenor.services

import iv.nakonechnyi.giftenor.data.GifInfo
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class JsonParserTest {

    private lateinit var gifInfo: GifInfo
    private lateinit var notCorrectGigInfo: GifInfo
    private lateinit var correctJson: String
    private lateinit var notCorrectJson: String

    @Before
    fun setUp() {

     gifInfo = GifInfo(
         id = "5611744",
         created = 1466798368.470177,
         title = "wolf",
         preview = "https://media.tenor.com/images/136aea16319644828aab0d5a463125c0/raw",
         url = "https://media.tenor.com/images/a4239487938c5b1daa7615c657f2bf89/tenor.gif"
     )

        notCorrectGigInfo = GifInfo(
            id = "5611744",
            created = 1466798368.470,
            title = "wolf",
            preview = "https://media.tenor.com/images/136aea16319644828aab0d5a463125c0/raw",
            url = "https://media.tenor.com/images/a4239487938c5b1daa7615c657f2bf89/tenor.gif"
        )

        correctJson = "{\n" +
                "  \"weburl\": \"https://tenor.com/search/wolf-gifs\", \n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"tags\": [], \n" +
                "      \"url\": \"https://tenor.com/xH2a.gif\", \n" +
                "      \"media\": [\n" +
                "        {\n" +
                "          \"nanomp4\": {\n" +
                "            \"url\": \"https://media.tenor.com/videos/11bd817b1a1aa7bc012162eafcbdc878/mp4\", \n" +
                "            \"dims\": [\n" +
                "              150, \n" +
                "              84\n" +
                "            ], \n" +
                "            \"duration\": 2.4, \n" +
                "            \"preview\": \"https://media.tenor.com/images/a96dd4dccec003fd472654b73d73d023/tenor.png\", \n" +
                "            \"size\": 32870\n" +
                "          }, \n" +
                "          \"nanowebm\": {\n" +
                "            \"url\": \"https://media.tenor.com/videos/fc635613d6b2354253f3b2d1c954a01e/webm\", \n" +
                "            \"dims\": [\n" +
                "              150, \n" +
                "              84\n" +
                "            ], \n" +
                "            \"preview\": \"https://media.tenor.com/images/a96dd4dccec003fd472654b73d73d023/tenor.png\", \n" +
                "            \"size\": 28113\n" +
                "          }, \n" +
                "          \"tinygif\": {\n" +
                "            \"url\": \"https://media.tenor.com/images/5b53213bcdc714f4e2f25af48122fdb6/tenor.gif\", \n" +
                "            \"dims\": [\n" +
                "              220, \n" +
                "              123\n" +
                "            ], \n" +
                "            \"preview\": \"https://media.tenor.com/images/ad82215ec79fe7b05c089778f746a800/raw\", \n" +
                "            \"size\": 100792\n" +
                "          }, \n" +
                "          \"tinymp4\": {\n" +
                "            \"url\": \"https://media.tenor.com/videos/e5eed255cd1f5eea763d95f361bd289f/mp4\", \n" +
                "            \"dims\": [\n" +
                "              320, \n" +
                "              178\n" +
                "            ], \n" +
                "            \"duration\": 2.4, \n" +
                "            \"preview\": \"https://media.tenor.com/images/eb000f9ffe8d83b96bdce61f2558172f/tenor.png\", \n" +
                "            \"size\": 109169\n" +
                "          }, \n" +
                "          \"tinywebm\": {\n" +
                "            \"url\": \"https://media.tenor.com/videos/6a6dd242b0c79bb902d9fa73a28853c1/webm\", \n" +
                "            \"dims\": [\n" +
                "              320, \n" +
                "              178\n" +
                "            ], \n" +
                "            \"preview\": \"https://media.tenor.com/images/eb000f9ffe8d83b96bdce61f2558172f/tenor.png\", \n" +
                "            \"size\": 61613\n" +
                "          }, \n" +
                "          \"webm\": {\n" +
                "            \"url\": \"https://media.tenor.com/videos/7f4084bd73ab50c1b3662b5e5cc46052/webm\", \n" +
                "            \"dims\": [\n" +
                "              500, \n" +
                "              280\n" +
                "            ], \n" +
                "            \"preview\": \"https://media.tenor.com/images/136aea16319644828aab0d5a463125c0/raw\", \n" +
                "            \"size\": 63706\n" +
                "          }, \n" +
                "          \"gif\": {\n" +
                "            \"url\": \"https://media.tenor.com/images/a4239487938c5b1daa7615c657f2bf89/tenor.gif\", \n" +
                "            \"dims\": [\n" +
                "              498, \n" +
                "              278\n" +
                "            ], \n" +
                "            \"preview\": \"https://media.tenor.com/images/136aea16319644828aab0d5a463125c0/raw\", \n" +
                "            \"size\": 1451999\n" +
                "          }, \n" +
                "          \"mp4\": {\n" +
                "            \"url\": \"https://media.tenor.com/videos/75e6f7c1fca8ce17bac7cfd1abb1da9d/mp4\", \n" +
                "            \"dims\": [\n" +
                "              500, \n" +
                "              280\n" +
                "            ], \n" +
                "            \"duration\": 2.4, \n" +
                "            \"preview\": \"https://media.tenor.com/images/136aea16319644828aab0d5a463125c0/raw\", \n" +
                "            \"size\": 149692\n" +
                "          }, \n" +
                "          \"loopedmp4\": {\n" +
                "            \"url\": \"https://media.tenor.com/videos/bfbda0224cb519aa91d242a10a5da6b3/mp4\", \n" +
                "            \"dims\": [\n" +
                "              500, \n" +
                "              280\n" +
                "            ], \n" +
                "            \"duration\": 7.2, \n" +
                "            \"preview\": \"https://media.tenor.com/images/136aea16319644828aab0d5a463125c0/raw\"\n" +
                "          }, \n" +
                "          \"mediumgif\": {\n" +
                "            \"url\": \"https://media.tenor.com/images/598dcdcb0750b208913dc01c10480b37/tenor.gif\", \n" +
                "            \"dims\": [\n" +
                "              498, \n" +
                "              278\n" +
                "            ], \n" +
                "            \"preview\": \"https://media.tenor.com/images/e8f898c9c950aea1adee8cc20d2af811/tenor.gif\", \n" +
                "            \"size\": 458648\n" +
                "          }, \n" +
                "          \"nanogif\": {\n" +
                "            \"url\": \"https://media.tenor.com/images/d62baa3022957e4009c15c70b52e9151/tenor.gif\", \n" +
                "            \"dims\": [\n" +
                "              160, \n" +
                "              90\n" +
                "            ], \n" +
                "            \"preview\": \"https://media.tenor.com/images/495e668edc16796925c6d9c184ad9289/raw\", \n" +
                "            \"size\": 57522\n" +
                "          }\n" +
                "        }\n" +
                "      ], \n" +
                "      \"created\": 1466798368.470177, \n" +
                "      \"shares\": 1, \n" +
                "      \"itemurl\": \"https://tenor.com/view/wolf-wolves-wilderness-wild-wolf-howl-gif-5611744\", \n" +
                "      \"composite\": null, \n" +
                "      \"hasaudio\": false, \n" +
                "      \"title\": \"wolf\", \n" +
                "      \"id\": \"5611744\"\n" +
                "    }\n" +
                "  ], \n" +
                "  \"next\": \"1\"\n" +
                "}"

        notCorrectJson = "{}"

    }

    @Test
    fun check_That_Parsed_Json_Equals_To_Expected_GifInfo_Instance() {

        val expected = listOf(gifInfo)
        val actual = JsonParser.parse(correctJson)

        assertThat(actual, Matchers.isA(List::class.java))
        assertEquals(expected, actual)

    }

    @Test
    fun check_That_Parsed_Json_Not_Equals_To_Expected_GifInfo_Instance() {

        val expected = arrayListOf(notCorrectGigInfo)
        val actual = JsonParser.parse(correctJson)

        assertThat(expected, Matchers.isA(List::class.java))
        assertThat(actual, Matchers.isA(List::class.java))
        assertNotEquals(expected, actual)
        assertNotEquals(expected[0], actual[0])

    }

    @Test
    fun check_That_Not_Thrown_An_Exception_When_JsonParser_Fails_Or_Receive_Null_As_Response_And_Return_An_Empty_List(){

        val expected = emptyList<GifInfo>()
        val actual1 = JsonParser.parse(notCorrectJson) // here will be thrown an error, but result should be an empty list
        val actual2 = JsonParser.parse(null)

        assertEquals(expected, actual1)
        assertEquals(expected, actual2)

    }

    @Test
    fun getNext() {

        JsonParser.reset()

        JsonParser.parse(correctJson)

        assertEquals(JsonParser.next, "1")

    }
}