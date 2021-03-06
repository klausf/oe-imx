DESCRIPTION = "Edje is the Enlightenment graphical design & layout library"
DEPENDS = "eet evas ecore embryo edje-native"
LICENSE = "MIT BSD"
PV = "0.9.92.060+svnr${SRCREV}"
PR = "r4"

inherit efl

# gain some extra performance at the expense of RAM - generally i'd say bad
# and a possible source of bugs
#EXTRA_OECONF = "--enable-edje-program-cache"

PACKAGES =+ "${PN}-utils"
RDEPENDS_${PN}-utils = "cpp cpp-symlinks embryo-tests"

RRECOMMENDS_${PN}-utils = "\
  evas-saver-png \
  evas-saver-jpeg \
  evas-saver-eet \
"

DEBIAN_NOAUTONAME_${PN}-utils = "1"
# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

RREPLACES_${PN} = "libedje-ver-pre-svn-00-0 libedje-ver-pre-svn-01-0"

FILES_${PN}-utils = "\
  ${bindir}/edje_* \
  ${datadir}/edje/include/edje.inc \
"
