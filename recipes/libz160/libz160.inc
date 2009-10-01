SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "GPU UIO library for amd z160 i.mx chips"
LICENSE = "PROPRIETARY"

SRC_URI="${FSL_IPP}/${PN}-${PV}.tar.gz"
COMPATIBLE_MACHINE = "babbage"
PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "${libdir}/*"

do_compile(){
        oe_runmake
}
do_install(){
        oe_runmake install DESTDIR=${D}/${prefix}
}
do_stage () {
        oe_runmake install DESTDIR=${STAGING_DIR_HOST}${prefix}
}
