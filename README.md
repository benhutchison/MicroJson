MicroJson
=========

A lightweight JSON model and parser for Scala and Scala.js; turns Strings into JSON and back again. 

The main benefit of this library is that the code is pure scala and identical across JVM and JS.
It's the default pickle format for my serialisation library [prickle](https://github.com/benhutchison/prickle).

##Getting MicroJson

Scala.jvm
`"com.github.benhutchison" %% "microjson" % "1.2"

Scala.js 0.6+
`"com.github.benhutchison" %%% "microjson" % "1.2"

## Changelog

| Version | When   | Changes |
| --------| -------| --------|
| 1.0     | Oct 14 | Initial release with Prickle |
| 1.1     | Feb 15 | Scala.js 0.6 |
| 1.2     | Apr 15 | Performance fixes as revealed by BooPickle tests |

##Notes

It's based on Haoyi Li's work, which was embedded into an earlier version of [uPickle](https://github.com/lihaoyi/upickle).
I asked Haoyi if he'd be willing to publish it separately on Jul 3, 2014. He didn't want to himeself but he gave me permission to if I wished to.
It's not as fast as Jawn or native browser parsing, which is why uPickle stopped embedding it.


