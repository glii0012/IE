<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://codex.wordpress.org/Editing_wp-config.php
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'dementiacare' );

/** MySQL database username */
define( 'DB_USER', 'root' );

/** MySQL database password */
define( 'DB_PASSWORD', '' );

/** MySQL hostname */
define( 'DB_HOST', 'localhost' );

/** Database Charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The Database Collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         '#OCbUb_Cen|>`6@A91%[JbZEuVT1P_Gkn]VNBZKN=GG-*fev<ssN_Az0lmOd.&_?' );
define( 'SECURE_AUTH_KEY',  'fHU%LJ9G~pm+pR.;7SgsS+d7xu#cdqM]H&K)wEBdEI^f1.OxtD5)ycVPLy>r,{2&' );
define( 'LOGGED_IN_KEY',    '0;/eNdjC{)cMx}ueg:+j_ZFNTE})@r}&1T+r~mI.ca7>2d[0SN@M%gQ.?caVRRz5' );
define( 'NONCE_KEY',        'rw~UO`Xjm;@AZ|i$tUN}!Elh,4J$p%y,evnd>Bu==Y~v?!{(WWw3LiK&f<3P{e?l' );
define( 'AUTH_SALT',        'j=!6<vN2xy/~n~/FX wTL1{x>]g>.c2.Cw@pJragBS}zO:^+z,],lqH|?1$&x4xC' );
define( 'SECURE_AUTH_SALT', 'c)|/+*CYbr$d_}1g+qsR10swJb,Ej:qHyHZvI3XJ(0kTZ_qe=YNWh=.Hz6p}BG=+' );
define( 'LOGGED_IN_SALT',   'MhULUQ)6uV lzuhyGeAOdt?c$;; ^T39t-^~Ug%o^r_*sLVVj*%?yHE.@Gm=oolG' );
define( 'NONCE_SALT',       'FkVMC)ZIHuF6]KQe5K;65>B~?wYi/OS6(k|JV`_^^#2_@pL%]*3n4N2*#Fr|4@<m' );

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'dc_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the Codex.
 *
 * @link https://codex.wordpress.org/Debugging_in_WordPress
 */
define( 'WP_DEBUG', false );

/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', dirname( __FILE__ ) . '/' );
}

/** Sets up WordPress vars and included files. */
require_once( ABSPATH . 'wp-settings.php' );
