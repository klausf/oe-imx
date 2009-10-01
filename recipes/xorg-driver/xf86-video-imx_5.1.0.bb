require xf86-video-common.inc

EXTRA_OECONF += "--disable-xvmc"

FSLPN="xserver-xorg-video-imx"

DESCRIPTION = "X.Org X server -- fbdev display driver"
DEPENDS += "virtual/libx11 libz160 imx-lib  virtual/kernel xextproto libxext"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI="${FSL_IPP}/${FSLPN}-${PV}.tar.gz"

CFLAGS_append+=" -I${STAGING_KERNEL_DIR}/include"

# AC_CHECK_FILE doesn't work when cross compiling, so we create a replacement
# macro that simply assumes the test succeeds. 
# and this package includes hardcoded /usr/share/aclocal reference
do_configure_prepend () {
    echo 'AC_DEFUN(CC_AC_CHECK_FILE, $2)' > configure.ac.new
    sed 's/AC_CHECK_FILE/CC_AC_CHECK_FILE/g' configure.ac | sed 's/AC_CONFIG_MACRO_DIR...usr.share.aclocal..//' >> configure.ac.new
    mv configure.ac.new configure.ac
}

