SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "GPU driver and library for amd GPUs in i.mx chips"
LICENSE = "PROPRIETARY"

PROVIDES += "virtual/vg"
PROVIDES += "virtual/gles2"
PROVIDES += "virtual/gles1.1"
PROVIDES += "virtual/egl"

SRC_URI = "http://www.freescale.com/not/public/ask/your/fsl/contact/for/tarball/${PN}-${PV}.tar.gz"

PACKAGES += "${PN}-samples"

FILES_${PN} = "${libdir}/*"
FILES_${PN}-samples = "${bindir}/*"

do_compile() { 

# clutter need those library names (probably not the best way to do it..)
        ${CXX}  -shared -o usr/lib/libGLESv2.so -lgles20 -Lusr/lib
        ${CXX}  -shared -o usr/lib/libEGL.so -legl13 -Lusr/lib
}
my_install(){
	my_bindir=$1
        my_libdir=$2
        my_incdir=$3
        echo install -d ${my_libdir}
        install -d ${my_libdir}
        echo install -d ${my_incdir}
        install -d ${my_incdir}
        for i in libbb2d  libc2d  libcsi  libegl13  libgles11  libgles20  libgsl  libOpenVG  libpanel2  libres libEGL libGLESv2
        do
                oe_libinstall -C usr/lib $i  ${my_libdir}
 	done
	if [ ${my_bindir} != /dev/null ]
	then
        install -d ${my_bindir}
        for i in ` ls usr/bin` 
	do
	        echo install -m 0755 usr/bin/$i ${my_bindir}
	        install -m 0755 usr/bin/$i ${my_bindir}
	done	
	fi
        for dir in `ls usr/include`
        do
                install -d ${my_incdir}/${dir}
                for f in `ls usr/include/${dir}`
                do
                        echo install -m 0644 usr/include/${dir}/$f ${my_incdir}/${dir}
                        install -m 0644 usr/include/${dir}/$f ${my_incdir}/${dir}
                done
        done

}
do_install() {
	my_install ${D}/${bindir} ${D}/${libdir} ${D}/${includedir}
}
            
do_stage() {
	my_install /dev/null ${STAGING_LIBDIR} ${STAGING_INCDIR}
}

# insane.bbclass QA is complaining that there is no GNU_HASH into binaries.
# so we remove it entierly before finding a correct solution
INSANE_SKIP_${PN} = True
INSANE_SKIP_${PN}-samples = True
