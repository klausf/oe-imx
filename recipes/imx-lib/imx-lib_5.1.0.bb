# @todo, should make one package per library. for now, only libipu is supported
# other libs may not compile

SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "IPU  user space library for i.mx chips"
LICENSE = "LGPL"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${libdir}/*"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI="${FSL_IPP}/${PN}-${PV}.tar.gz"
EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} PLATFORM=${MACHINE_ARCH}"

IMXLIB_DIRS=ipu
do_compile () {
        for dir in ${IMXLIB_DIRS}
        do
        oe_runmake -C $dir "INCLUDE=-I${STAGING_KERNEL_DIR}/include" 
        done
}
do_install () {
        for dir in ${IMXLIB_DIRS}
        do
        oe_runmake -C $dir install DEST_DIR=${D}
        done
}
do_stage () {
        for dir in ${IMXLIB_DIRS}
        do
        oe_runmake -C $dir install DEST_DIR=${STAGING_DIR_HOST}
        done
}

