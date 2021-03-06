DESCRIPTION = "Qt interface for choosing which configured network to connect \
to. It also provides a method for browsing 802.11 SSID scan results, an event \
history log of messages generated by wpa_supplicant, and a method to add or \
edit wpa_supplicant networks."
SECTION = "network"
LICENSE = "GPL BSD"
HOMEPAGE = "http://hostap.epitest.fi/wpa_supplicant/"
RDEPENDS = "wpa-supplicant"
PR = "r1"

SRC_URI = "http://hostap.epitest.fi/releases/wpa_supplicant-${PV}.tar.gz "

S = "${WORKDIR}/wpa_supplicant-${PV}/wpa_gui/"

inherit qmake qt3x11

EXTRA_QMAKEVARS_POST += "CONFIG+=thread"

do_install () {
	install -d ${D}${sbindir}
	install -m 755 wpa_gui ${D}${sbindir}
}
