//===---------- Support/UnicodeCaseFold.cpp -------------------------------===//
//
// This file was generated by utils/unicode-case-fold.py from the Unicode
// case folding database at
//    http://www.unicode.org/Public/9.0.0/ucd/CaseFolding.txt
//
// To regenerate this file, run:
//   utils/unicode-case-fold.py
//     "http://www.unicode.org/Public/9.0.0/ucd/CaseFolding.txt"
//     > lib/Support/UnicodeCaseFold.cpp
//
//===----------------------------------------------------------------------===//

#include "wpi/Unicode.h"

int wpi::sys::unicode::foldCharSimple(int C) {
  if (C < 0x0041)
    return C;
  // 26 characters
  if (C <= 0x005a)
    return C + 32;
  // MICRO SIGN
  if (C == 0x00b5)
    return 0x03bc;
  if (C < 0x00c0)
    return C;
  // 23 characters
  if (C <= 0x00d6)
    return C + 32;
  if (C < 0x00d8)
    return C;
  // 7 characters
  if (C <= 0x00de)
    return C + 32;
  if (C < 0x0100)
    return C;
  // 24 characters
  if (C <= 0x012e)
    return C | 1;
  if (C < 0x0132)
    return C;
  // 3 characters
  if (C <= 0x0136)
    return C | 1;
  if (C < 0x0139)
    return C;
  // 8 characters
  if (C <= 0x0147 && C % 2 == 1)
    return C + 1;
  if (C < 0x014a)
    return C;
  // 23 characters
  if (C <= 0x0176)
    return C | 1;
  // LATIN CAPITAL LETTER Y WITH DIAERESIS
  if (C == 0x0178)
    return 0x00ff;
  if (C < 0x0179)
    return C;
  // 3 characters
  if (C <= 0x017d && C % 2 == 1)
    return C + 1;
  // LATIN SMALL LETTER LONG S
  if (C == 0x017f)
    return 0x0073;
  // LATIN CAPITAL LETTER B WITH HOOK
  if (C == 0x0181)
    return 0x0253;
  if (C < 0x0182)
    return C;
  // 2 characters
  if (C <= 0x0184)
    return C | 1;
  // LATIN CAPITAL LETTER OPEN O
  if (C == 0x0186)
    return 0x0254;
  // LATIN CAPITAL LETTER C WITH HOOK
  if (C == 0x0187)
    return 0x0188;
  if (C < 0x0189)
    return C;
  // 2 characters
  if (C <= 0x018a)
    return C + 205;
  // LATIN CAPITAL LETTER D WITH TOPBAR
  if (C == 0x018b)
    return 0x018c;
  // LATIN CAPITAL LETTER REVERSED E
  if (C == 0x018e)
    return 0x01dd;
  // LATIN CAPITAL LETTER SCHWA
  if (C == 0x018f)
    return 0x0259;
  // LATIN CAPITAL LETTER OPEN E
  if (C == 0x0190)
    return 0x025b;
  // LATIN CAPITAL LETTER F WITH HOOK
  if (C == 0x0191)
    return 0x0192;
  // LATIN CAPITAL LETTER G WITH HOOK
  if (C == 0x0193)
    return 0x0260;
  // LATIN CAPITAL LETTER GAMMA
  if (C == 0x0194)
    return 0x0263;
  // LATIN CAPITAL LETTER IOTA
  if (C == 0x0196)
    return 0x0269;
  // LATIN CAPITAL LETTER I WITH STROKE
  if (C == 0x0197)
    return 0x0268;
  // LATIN CAPITAL LETTER K WITH HOOK
  if (C == 0x0198)
    return 0x0199;
  // LATIN CAPITAL LETTER TURNED M
  if (C == 0x019c)
    return 0x026f;
  // LATIN CAPITAL LETTER N WITH LEFT HOOK
  if (C == 0x019d)
    return 0x0272;
  // LATIN CAPITAL LETTER O WITH MIDDLE TILDE
  if (C == 0x019f)
    return 0x0275;
  if (C < 0x01a0)
    return C;
  // 3 characters
  if (C <= 0x01a4)
    return C | 1;
  // LATIN LETTER YR
  if (C == 0x01a6)
    return 0x0280;
  // LATIN CAPITAL LETTER TONE TWO
  if (C == 0x01a7)
    return 0x01a8;
  // LATIN CAPITAL LETTER ESH
  if (C == 0x01a9)
    return 0x0283;
  // LATIN CAPITAL LETTER T WITH HOOK
  if (C == 0x01ac)
    return 0x01ad;
  // LATIN CAPITAL LETTER T WITH RETROFLEX HOOK
  if (C == 0x01ae)
    return 0x0288;
  // LATIN CAPITAL LETTER U WITH HORN
  if (C == 0x01af)
    return 0x01b0;
  if (C < 0x01b1)
    return C;
  // 2 characters
  if (C <= 0x01b2)
    return C + 217;
  if (C < 0x01b3)
    return C;
  // 2 characters
  if (C <= 0x01b5 && C % 2 == 1)
    return C + 1;
  // LATIN CAPITAL LETTER EZH
  if (C == 0x01b7)
    return 0x0292;
  if (C < 0x01b8)
    return C;
  // 2 characters
  if (C <= 0x01bc && C % 4 == 0)
    return C + 1;
  // LATIN CAPITAL LETTER DZ WITH CARON
  if (C == 0x01c4)
    return 0x01c6;
  // LATIN CAPITAL LETTER D WITH SMALL LETTER Z WITH CARON
  if (C == 0x01c5)
    return 0x01c6;
  // LATIN CAPITAL LETTER LJ
  if (C == 0x01c7)
    return 0x01c9;
  // LATIN CAPITAL LETTER L WITH SMALL LETTER J
  if (C == 0x01c8)
    return 0x01c9;
  // LATIN CAPITAL LETTER NJ
  if (C == 0x01ca)
    return 0x01cc;
  if (C < 0x01cb)
    return C;
  // 9 characters
  if (C <= 0x01db && C % 2 == 1)
    return C + 1;
  if (C < 0x01de)
    return C;
  // 9 characters
  if (C <= 0x01ee)
    return C | 1;
  // LATIN CAPITAL LETTER DZ
  if (C == 0x01f1)
    return 0x01f3;
  if (C < 0x01f2)
    return C;
  // 2 characters
  if (C <= 0x01f4)
    return C | 1;
  // LATIN CAPITAL LETTER HWAIR
  if (C == 0x01f6)
    return 0x0195;
  // LATIN CAPITAL LETTER WYNN
  if (C == 0x01f7)
    return 0x01bf;
  if (C < 0x01f8)
    return C;
  // 20 characters
  if (C <= 0x021e)
    return C | 1;
  // LATIN CAPITAL LETTER N WITH LONG RIGHT LEG
  if (C == 0x0220)
    return 0x019e;
  if (C < 0x0222)
    return C;
  // 9 characters
  if (C <= 0x0232)
    return C | 1;
  // LATIN CAPITAL LETTER A WITH STROKE
  if (C == 0x023a)
    return 0x2c65;
  // LATIN CAPITAL LETTER C WITH STROKE
  if (C == 0x023b)
    return 0x023c;
  // LATIN CAPITAL LETTER L WITH BAR
  if (C == 0x023d)
    return 0x019a;
  // LATIN CAPITAL LETTER T WITH DIAGONAL STROKE
  if (C == 0x023e)
    return 0x2c66;
  // LATIN CAPITAL LETTER GLOTTAL STOP
  if (C == 0x0241)
    return 0x0242;
  // LATIN CAPITAL LETTER B WITH STROKE
  if (C == 0x0243)
    return 0x0180;
  // LATIN CAPITAL LETTER U BAR
  if (C == 0x0244)
    return 0x0289;
  // LATIN CAPITAL LETTER TURNED V
  if (C == 0x0245)
    return 0x028c;
  if (C < 0x0246)
    return C;
  // 5 characters
  if (C <= 0x024e)
    return C | 1;
  // COMBINING GREEK YPOGEGRAMMENI
  if (C == 0x0345)
    return 0x03b9;
  if (C < 0x0370)
    return C;
  // 2 characters
  if (C <= 0x0372)
    return C | 1;
  // GREEK CAPITAL LETTER PAMPHYLIAN DIGAMMA
  if (C == 0x0376)
    return 0x0377;
  // GREEK CAPITAL LETTER YOT
  if (C == 0x037f)
    return 0x03f3;
  // GREEK CAPITAL LETTER ALPHA WITH TONOS
  if (C == 0x0386)
    return 0x03ac;
  if (C < 0x0388)
    return C;
  // 3 characters
  if (C <= 0x038a)
    return C + 37;
  // GREEK CAPITAL LETTER OMICRON WITH TONOS
  if (C == 0x038c)
    return 0x03cc;
  if (C < 0x038e)
    return C;
  // 2 characters
  if (C <= 0x038f)
    return C + 63;
  if (C < 0x0391)
    return C;
  // 17 characters
  if (C <= 0x03a1)
    return C + 32;
  if (C < 0x03a3)
    return C;
  // 9 characters
  if (C <= 0x03ab)
    return C + 32;
  // GREEK SMALL LETTER FINAL SIGMA
  if (C == 0x03c2)
    return 0x03c3;
  // GREEK CAPITAL KAI SYMBOL
  if (C == 0x03cf)
    return 0x03d7;
  // GREEK BETA SYMBOL
  if (C == 0x03d0)
    return 0x03b2;
  // GREEK THETA SYMBOL
  if (C == 0x03d1)
    return 0x03b8;
  // GREEK PHI SYMBOL
  if (C == 0x03d5)
    return 0x03c6;
  // GREEK PI SYMBOL
  if (C == 0x03d6)
    return 0x03c0;
  if (C < 0x03d8)
    return C;
  // 12 characters
  if (C <= 0x03ee)
    return C | 1;
  // GREEK KAPPA SYMBOL
  if (C == 0x03f0)
    return 0x03ba;
  // GREEK RHO SYMBOL
  if (C == 0x03f1)
    return 0x03c1;
  // GREEK CAPITAL THETA SYMBOL
  if (C == 0x03f4)
    return 0x03b8;
  // GREEK LUNATE EPSILON SYMBOL
  if (C == 0x03f5)
    return 0x03b5;
  // GREEK CAPITAL LETTER SHO
  if (C == 0x03f7)
    return 0x03f8;
  // GREEK CAPITAL LUNATE SIGMA SYMBOL
  if (C == 0x03f9)
    return 0x03f2;
  // GREEK CAPITAL LETTER SAN
  if (C == 0x03fa)
    return 0x03fb;
  if (C < 0x03fd)
    return C;
  // 3 characters
  if (C <= 0x03ff)
    return C + -130;
  if (C < 0x0400)
    return C;
  // 16 characters
  if (C <= 0x040f)
    return C + 80;
  if (C < 0x0410)
    return C;
  // 32 characters
  if (C <= 0x042f)
    return C + 32;
  if (C < 0x0460)
    return C;
  // 17 characters
  if (C <= 0x0480)
    return C | 1;
  if (C < 0x048a)
    return C;
  // 27 characters
  if (C <= 0x04be)
    return C | 1;
  // CYRILLIC LETTER PALOCHKA
  if (C == 0x04c0)
    return 0x04cf;
  if (C < 0x04c1)
    return C;
  // 7 characters
  if (C <= 0x04cd && C % 2 == 1)
    return C + 1;
  if (C < 0x04d0)
    return C;
  // 48 characters
  if (C <= 0x052e)
    return C | 1;
  if (C < 0x0531)
    return C;
  // 38 characters
  if (C <= 0x0556)
    return C + 48;
  if (C < 0x10a0)
    return C;
  // 38 characters
  if (C <= 0x10c5)
    return C + 7264;
  if (C < 0x10c7)
    return C;
  // 2 characters
  if (C <= 0x10cd && C % 6 == 5)
    return C + 7264;
  if (C < 0x13f8)
    return C;
  // 6 characters
  if (C <= 0x13fd)
    return C + -8;
  // CYRILLIC SMALL LETTER ROUNDED VE
  if (C == 0x1c80)
    return 0x0432;
  // CYRILLIC SMALL LETTER LONG-LEGGED DE
  if (C == 0x1c81)
    return 0x0434;
  // CYRILLIC SMALL LETTER NARROW O
  if (C == 0x1c82)
    return 0x043e;
  if (C < 0x1c83)
    return C;
  // 2 characters
  if (C <= 0x1c84)
    return C + -6210;
  // CYRILLIC SMALL LETTER THREE-LEGGED TE
  if (C == 0x1c85)
    return 0x0442;
  // CYRILLIC SMALL LETTER TALL HARD SIGN
  if (C == 0x1c86)
    return 0x044a;
  // CYRILLIC SMALL LETTER TALL YAT
  if (C == 0x1c87)
    return 0x0463;
  // CYRILLIC SMALL LETTER UNBLENDED UK
  if (C == 0x1c88)
    return 0xa64b;
  if (C < 0x1e00)
    return C;
  // 75 characters
  if (C <= 0x1e94)
    return C | 1;
  // LATIN SMALL LETTER LONG S WITH DOT ABOVE
  if (C == 0x1e9b)
    return 0x1e61;
  // LATIN CAPITAL LETTER SHARP S
  if (C == 0x1e9e)
    return 0x00df;
  if (C < 0x1ea0)
    return C;
  // 48 characters
  if (C <= 0x1efe)
    return C | 1;
  if (C < 0x1f08)
    return C;
  // 8 characters
  if (C <= 0x1f0f)
    return C + -8;
  if (C < 0x1f18)
    return C;
  // 6 characters
  if (C <= 0x1f1d)
    return C + -8;
  if (C < 0x1f28)
    return C;
  // 8 characters
  if (C <= 0x1f2f)
    return C + -8;
  if (C < 0x1f38)
    return C;
  // 8 characters
  if (C <= 0x1f3f)
    return C + -8;
  if (C < 0x1f48)
    return C;
  // 6 characters
  if (C <= 0x1f4d)
    return C + -8;
  if (C < 0x1f59)
    return C;
  // 4 characters
  if (C <= 0x1f5f && C % 2 == 1)
    return C + -8;
  if (C < 0x1f68)
    return C;
  // 8 characters
  if (C <= 0x1f6f)
    return C + -8;
  if (C < 0x1f88)
    return C;
  // 8 characters
  if (C <= 0x1f8f)
    return C + -8;
  if (C < 0x1f98)
    return C;
  // 8 characters
  if (C <= 0x1f9f)
    return C + -8;
  if (C < 0x1fa8)
    return C;
  // 8 characters
  if (C <= 0x1faf)
    return C + -8;
  if (C < 0x1fb8)
    return C;
  // 2 characters
  if (C <= 0x1fb9)
    return C + -8;
  if (C < 0x1fba)
    return C;
  // 2 characters
  if (C <= 0x1fbb)
    return C + -74;
  // GREEK CAPITAL LETTER ALPHA WITH PROSGEGRAMMENI
  if (C == 0x1fbc)
    return 0x1fb3;
  // GREEK PROSGEGRAMMENI
  if (C == 0x1fbe)
    return 0x03b9;
  if (C < 0x1fc8)
    return C;
  // 4 characters
  if (C <= 0x1fcb)
    return C + -86;
  // GREEK CAPITAL LETTER ETA WITH PROSGEGRAMMENI
  if (C == 0x1fcc)
    return 0x1fc3;
  if (C < 0x1fd8)
    return C;
  // 2 characters
  if (C <= 0x1fd9)
    return C + -8;
  if (C < 0x1fda)
    return C;
  // 2 characters
  if (C <= 0x1fdb)
    return C + -100;
  if (C < 0x1fe8)
    return C;
  // 2 characters
  if (C <= 0x1fe9)
    return C + -8;
  if (C < 0x1fea)
    return C;
  // 2 characters
  if (C <= 0x1feb)
    return C + -112;
  // GREEK CAPITAL LETTER RHO WITH DASIA
  if (C == 0x1fec)
    return 0x1fe5;
  if (C < 0x1ff8)
    return C;
  // 2 characters
  if (C <= 0x1ff9)
    return C + -128;
  if (C < 0x1ffa)
    return C;
  // 2 characters
  if (C <= 0x1ffb)
    return C + -126;
  // GREEK CAPITAL LETTER OMEGA WITH PROSGEGRAMMENI
  if (C == 0x1ffc)
    return 0x1ff3;
  // OHM SIGN
  if (C == 0x2126)
    return 0x03c9;
  // KELVIN SIGN
  if (C == 0x212a)
    return 0x006b;
  // ANGSTROM SIGN
  if (C == 0x212b)
    return 0x00e5;
  // TURNED CAPITAL F
  if (C == 0x2132)
    return 0x214e;
  if (C < 0x2160)
    return C;
  // 16 characters
  if (C <= 0x216f)
    return C + 16;
  // ROMAN NUMERAL REVERSED ONE HUNDRED
  if (C == 0x2183)
    return 0x2184;
  if (C < 0x24b6)
    return C;
  // 26 characters
  if (C <= 0x24cf)
    return C + 26;
  if (C < 0x2c00)
    return C;
  // 47 characters
  if (C <= 0x2c2e)
    return C + 48;
  // LATIN CAPITAL LETTER L WITH DOUBLE BAR
  if (C == 0x2c60)
    return 0x2c61;
  // LATIN CAPITAL LETTER L WITH MIDDLE TILDE
  if (C == 0x2c62)
    return 0x026b;
  // LATIN CAPITAL LETTER P WITH STROKE
  if (C == 0x2c63)
    return 0x1d7d;
  // LATIN CAPITAL LETTER R WITH TAIL
  if (C == 0x2c64)
    return 0x027d;
  if (C < 0x2c67)
    return C;
  // 3 characters
  if (C <= 0x2c6b && C % 2 == 1)
    return C + 1;
  // LATIN CAPITAL LETTER ALPHA
  if (C == 0x2c6d)
    return 0x0251;
  // LATIN CAPITAL LETTER M WITH HOOK
  if (C == 0x2c6e)
    return 0x0271;
  // LATIN CAPITAL LETTER TURNED A
  if (C == 0x2c6f)
    return 0x0250;
  // LATIN CAPITAL LETTER TURNED ALPHA
  if (C == 0x2c70)
    return 0x0252;
  if (C < 0x2c72)
    return C;
  // 2 characters
  if (C <= 0x2c75 && C % 3 == 2)
    return C + 1;
  if (C < 0x2c7e)
    return C;
  // 2 characters
  if (C <= 0x2c7f)
    return C + -10815;
  if (C < 0x2c80)
    return C;
  // 50 characters
  if (C <= 0x2ce2)
    return C | 1;
  if (C < 0x2ceb)
    return C;
  // 2 characters
  if (C <= 0x2ced && C % 2 == 1)
    return C + 1;
  if (C < 0x2cf2)
    return C;
  // 2 characters
  if (C <= 0xa640 && C % 31054 == 11506)
    return C + 1;
  if (C < 0xa642)
    return C;
  // 22 characters
  if (C <= 0xa66c)
    return C | 1;
  if (C < 0xa680)
    return C;
  // 14 characters
  if (C <= 0xa69a)
    return C | 1;
  if (C < 0xa722)
    return C;
  // 7 characters
  if (C <= 0xa72e)
    return C | 1;
  if (C < 0xa732)
    return C;
  // 31 characters
  if (C <= 0xa76e)
    return C | 1;
  if (C < 0xa779)
    return C;
  // 2 characters
  if (C <= 0xa77b && C % 2 == 1)
    return C + 1;
  // LATIN CAPITAL LETTER INSULAR G
  if (C == 0xa77d)
    return 0x1d79;
  if (C < 0xa77e)
    return C;
  // 5 characters
  if (C <= 0xa786)
    return C | 1;
  // LATIN CAPITAL LETTER SALTILLO
  if (C == 0xa78b)
    return 0xa78c;
  // LATIN CAPITAL LETTER TURNED H
  if (C == 0xa78d)
    return 0x0265;
  if (C < 0xa790)
    return C;
  // 2 characters
  if (C <= 0xa792)
    return C | 1;
  if (C < 0xa796)
    return C;
  // 10 characters
  if (C <= 0xa7a8)
    return C | 1;
  // LATIN CAPITAL LETTER H WITH HOOK
  if (C == 0xa7aa)
    return 0x0266;
  // LATIN CAPITAL LETTER REVERSED OPEN E
  if (C == 0xa7ab)
    return 0x025c;
  // LATIN CAPITAL LETTER SCRIPT G
  if (C == 0xa7ac)
    return 0x0261;
  // LATIN CAPITAL LETTER L WITH BELT
  if (C == 0xa7ad)
    return 0x026c;
  // LATIN CAPITAL LETTER SMALL CAPITAL I
  if (C == 0xa7ae)
    return 0x026a;
  // LATIN CAPITAL LETTER TURNED K
  if (C == 0xa7b0)
    return 0x029e;
  // LATIN CAPITAL LETTER TURNED T
  if (C == 0xa7b1)
    return 0x0287;
  // LATIN CAPITAL LETTER J WITH CROSSED-TAIL
  if (C == 0xa7b2)
    return 0x029d;
  // LATIN CAPITAL LETTER CHI
  if (C == 0xa7b3)
    return 0xab53;
  if (C < 0xa7b4)
    return C;
  // 2 characters
  if (C <= 0xa7b6)
    return C | 1;
  if (C < 0xab70)
    return C;
  // 80 characters
  if (C <= 0xabbf)
    return C + -38864;
  if (C < 0xff21)
    return C;
  // 26 characters
  if (C <= 0xff3a)
    return C + 32;
  if (C < 0x10400)
    return C;
  // 40 characters
  if (C <= 0x10427)
    return C + 40;
  if (C < 0x104b0)
    return C;
  // 36 characters
  if (C <= 0x104d3)
    return C + 40;
  if (C < 0x10c80)
    return C;
  // 51 characters
  if (C <= 0x10cb2)
    return C + 64;
  if (C < 0x118a0)
    return C;
  // 32 characters
  if (C <= 0x118bf)
    return C + 32;
  if (C < 0x1e900)
    return C;
  // 34 characters
  if (C <= 0x1e921)
    return C + 34;

  return C;
}
