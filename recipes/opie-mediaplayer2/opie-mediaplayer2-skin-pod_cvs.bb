DESCRIPTION = "Skin for opie-mediaplayer2"
SECTION = "opie/multimedia"
PRIORITY = "optional"
LICENSE = "GPL"
APPNAME = "opieplayer2"
RPROVIDES = "opie-mediaplayer2-skin"
PV = "${OPIE_CVS_PV}"
PR = "r1"

SRC_URI = "${HANDHELDS_CVS};module=opie/pics"

do_install() {
	install -d ${D}${palmtopdir}/pics/${APPNAME}/skins/Pod/
        install -m 0644 ${WORKDIR}/pics/${APPNAME}/skins/Pod/*.png ${D}${palmtopdir}/pics/${APPNAME}/skins/Pod/
}

FILES_${PN} = "${palmtopdir}/pics/${APPNAME}/skins/Pod/*.png"
