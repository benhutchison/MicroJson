package microjson

import utest._
object JsonTests extends TestSuite{

  def get(json: JsValue, index: Int) =  json.asInstanceOf[JsArray].value(index).value

  def get(json: JsValue, index: Int, fieldName: String) =
    json.asInstanceOf[JsArray].value(index).asInstanceOf[JsObject].value(fieldName).value

  val tests = TestSuite{
    val ugly =
      """
        |[
        |    "JSON Test Pattern pass1",
        |    {"object with 1 member":["array with 1 element"]},
        |    {},
        |    [],
        |    -42,
        |    true,
        |    false,
        |    null,
        |    {
        |        "integer": 1234567890,
        |        "real": -9876.543210,
        |        "e": 0.123456789e-12,
        |        "E": 1.234567890E+34,
        |        "":  23456789012E66,
        |        "zero": 0,
        |        "one": 1,
        |        "space": " ",
        |        "quote": "\"",
        |        "backslash": "\\",
        |        "controls": "\b\f\n\r\t",
        |        "slash": "/ & \/",
        |        "alpha": "abcdefghijklmnopqrstuvwyz",
        |        "ALPHA": "ABCDEFGHIJKLMNOPQRSTUVWYZ",
        |        "digit": "0123456789",
        |        "0123456789": "digit",
        |        "special": "`1~!@#$%^&*()_+-={':[,]}|;.</>?",
        |        "hex": "\u0123\u4567\u89AB\uCDEF\uabcd\uef4A",
        |        "true": true,
        |        "false": false,
        |        "null": null,
        |        "array":[  ],
        |        "object":{  },
        |        "address": "50 St. James Street",
        |        "url": "http://www.JSON.org/",
        |        "comment": "// /* <!-- --",
        |        "# -- --> */": " ",
        |        " s p a c e d " :[1,2 , 3
        |
        |,
        |
        |4 , 5        ,          6           ,7        ],"compact":[1,2,3,4,5,6,7],
        |        "jsontext": "{\"object with 1 member\":[\"array with 1 element\"]}",
        |        "quotes": "&#34; \u005Cu0022 %22 0x22 034 &#x22;",
        |        "\/\\\"\uCAFE\uBABE\uAB98\uFCDE\ubcda\uef4A\b\f\n\r\t`1~!@#$%^&*()_+-=[]{}|;:',./<>?"
        |: "A key can be any string"
        |    },
        |    0.5 ,98.6
        |,
        |99.44
        |,
        |
        |1066,
        |1e1,
        |0.1e1,
        |1e-1,
        |1e00,2e+00,2e-00
        |,"rosebud"]
      """.stripMargin
    val parsed = Json.read(ugly)

    "correctness" - {
      val unparsed = Json.write(parsed)
      val reparsed = Json.read(unparsed)
      for (json <- Seq(parsed, reparsed)){
        assert(
          get(json, 0) == "JSON Test Pattern pass1",
          get(json, 8, "real") == "-9876.543210",
          get(json, 8, "comment") == "// /* <!-- --",
          get(json, 8, "jsontext") == "{\"object with 1 member\":[\"array with 1 element\"]}",
          get(json, 19) == "rosebud"
        )
      }
      (get(parsed, 19), get(reparsed, 19))
    }
    "performance" - {
      "read" - {
        var n = 0
        val start = System.currentTimeMillis()
        var parsed: JsValue = JsNull
        while(System.currentTimeMillis() < start + 5000){
          parsed = JsNull
          parsed = Json.read(ugly)
          n += 1
        }
        n
      }
      "write" - {
        var n = 0
        val start = System.currentTimeMillis()
        var output = ""
        while(System.currentTimeMillis() < start + 5000){
          output = Json.write(parsed)
          output = ""
          n += 1
        }
        n
      }
    }
    "access parsed values as objects" - {
      val root = parsed.asInstanceOf[JsArray]
      val obj = root.value(8).asInstanceOf[JsObject]
      assert(obj.isInstanceOf[JsObject])
      assert(obj.value("space").isInstanceOf[JsString])
      assert(obj.value("space").value == " ")
      assert(obj.value("integer").isInstanceOf[JsNumber])
      assert(obj.value("integer").value == "1234567890")
      assert(obj.value("true").value == true)
      assert(obj.value("jsontext").isInstanceOf[JsString])
      assert(obj.value("jsontext").value == """{"object with 1 member":["array with 1 element"]}""")
    }
  }
}

