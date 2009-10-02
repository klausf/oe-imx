require cairo.inc
#PROVIDES += "cairo"
#CONFLICTS += "cairo"
DEPENDS += "virtual/vg"
SRCREV = "67d40e5c7300c4082484dbda5c81808737bb2ac5"
PV = "1.9_git"
PR=0

SRC_URI = "\
        git://anongit.freedesktop.org/git/cairo;protocol=git"
S = "${WORKDIR}/git"

require cairo-fpu.inc
EXTRA_OECONF += "${@get_cairo_fpu_setting(bb, d)}"
EXTRA_OECONF += "--enable-vg=yes --enable-glx=no --enable-test-surfaces=yes --enable-egl=yes"
CFLAGS_append += "-D_LINUX"
inherit autotools_stage pkgconfig

export CC_FOR_BUILD = "${BUILD_CC}"
export CXX_FOR_BUILD = "${BUILD_CXX}"
export CPP_FOR_BUILD = "${BUILD_CPP}"
export CFLAGS_FOR_BUILD = "${BUILD_CFLAGS}"
export CXXFLAGS_FOR_BUILD = "${BUILD_CXXFLAGS}"
export CPPFLAGS_FOR_BUILD = "${BUILD_CPPFLAGS}"

do_configure_prepend(){
# create dummy */Makefile.am.features and ChangeLog to make automake happy
touch boilerplate/Makefile.am.features
touch src/Makefile.am.features
touch ChangeLog
}

