require linux.inc

PR = "r2"
SRCREV="rel_imx_2.6.28_4.5.0"
PV=2.6.28

S = "${WORKDIR}/git"

# Mark archs/machines that this kernel supports
COMPATIBLE_MACHINE = "(babbage|mx35pdk)"

SRC_URI = "git://github.com/tardyp/linux-2.6-imx.git;protocol=git\
	file://defconfig\
	file://armv7a-to-armv7-a.patch;patch=1" 
