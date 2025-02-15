/*******************************************************************************
 * Copyright (c) 2013-2016 iRPGUnit Project Team
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *******************************************************************************/

package de.tools400.rpgunit.core.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import de.tools400.rpgunit.core.RPGUnitCorePlugin;
import de.tools400.rpgunit.core.model.ibmi.I5ObjectName;

public final class Preferences {

    /**
     * The instance of this Singleton class.
     */
    private static Preferences instance;

    /**
     * Global preferences of the RPGUnit plugin.
     */
    private static IPreferenceStore preferenceStore;

    /**
     * Base configuration key:
     */
    private static final String RPGUNIT = "rpgunit"; //$NON-NLS-1$

    /**
     * Preferences version number
     */
    public static final String PREFERENCES_VERSION_NUMBER = RPGUNIT + ".preferencesVersionNumber"; //$NON-NLS-1$
    public static final int VERSION_NUMBER = 2;

    /**
     * Parameters of IBMi remote program 'RUPGMRMT':
     */
    public static final String RMTPGM = RPGUNIT + ".rmtpgm"; //$NON-NLS-1$

    public static final String RUN_ORDER = RMTPGM + ".order"; //$NON-NLS-1$

    public static final String RUN_ORDER_API = "*API"; //$NON-NLS-1$

    public static final String RUN_ORDER_REVERSE = "*REVERSE"; //$NON-NLS-1$

    public static final String REPORT_DETAIL = RMTPGM + ".detail"; //$NON-NLS-1$

    public static final String REPORT_DETAIL_BASIC = "*BASIC"; //$NON-NLS-1$

    public static final String REPORT_DETAIL_ALL = "*ALL"; //$NON-NLS-1$

    public static final String OUTPUT = RMTPGM + ".output"; //$NON-NLS-1$

    public static final String OUTPUT_NONE = "*NONE"; //$NON-NLS-1$

    public static final String OUTPUT_ERROR = "*ERROR"; //$NON-NLS-1$

    public static final String OUTPUT_ALWAYS = "*ALLWAYS"; //$NON-NLS-1$

    public static final String LIBRARY_LIST = RMTPGM + ".libraryList"; //$NON-NLS-1$

    public static final String LIBRARY_LIST_CURRENT = "*CURRENT"; //$NON-NLS-1$

    public static final String LIBRARY_LIST_JOBD = "*JOBD"; //$NON-NLS-1$

    public static final String JOBD_NAME = RMTPGM + ".jobDescription.name"; //$NON-NLS-1$

    public static final String JOBD_LIBRARY = RMTPGM + ".jobDescription.library"; //$NON-NLS-1$

    public static final String JOBD_NAME_DFT = "*DFT"; //$NON-NLS-1$

    public static final String JOBD_LIBRARY_LIBL = "*LIBL"; //$NON-NLS-1$

    public static final String JOBD_DEFAULT_NAME = "*DFT"; //$NON-NLS-1$

    public static final String JOBD_DEFAULT_LIBRARY = ""; //$NON-NLS-1$

    public static final String RECLAIM_RESOURCES = RMTPGM + ".reclaimResources"; //$NON-NLS-1$

    public static final String RECLAIM_RESOURCES_NO = "*NO"; //$NON-NLS-1$

    public static final String RECLAIM_RESOURCES_ALWAYS = "*ALWAYS"; //$NON-NLS-1$

    public static final String RECLAIM_RESOURCES_ONCE = "*ONCE"; //$NON-NLS-1$

    /**
     * RPGUnit warning messages:
     */
    public static final String WARN_MESSAGE = RPGUNIT + ".warnings"; //$NON-NLS-1$

    public static final String WARN_MESSAGE_SRC_OPTION = WARN_MESSAGE + ".source.option.srcstmt"; //$NON-NLS-1$

    public static final String WARN_MESSAGE_USER_DEFINED_ATTRIBUTE = WARN_MESSAGE + ".serviceprogram.userdefinedattribute"; //$NON-NLS-1$

    public static final String WARN_MESSAGE_OBSOLETE_PLUGINS_V1 = WARN_MESSAGE + ".obsolete.plugins.v1"; //$NON-NLS-1$

    public static final boolean WARN_MESSAGE_DEFAULT = true;

    /**
     * Values controlling report enablement.
     */
    public static final String REPORT = RPGUNIT + ".report"; //$NON-NLS-1$

    public static final String REPORT_DISABLED = REPORT + ".disabled"; //$NON-NLS-1$

    public static final Boolean REPORT_DISABLED_TRUE = true;

    public static final Boolean REPORT_DISABLED_FALSE = false;

    /**
     * Product library.
     */
    public static final String SYSTEM = RPGUNIT + ".system"; //$NON-NLS-1$

    public static final String PRODUCT_LIBRARY = SYSTEM + ".productLibrary"; //$NON-NLS-1$

    public static final String PRODUCT_LIBRARY_LIBL = "*LIBL"; //$NON-NLS-1$

    /**
     * Check test suite service program.
     */
    public static final String CHECK_TEST_SUITE = RPGUNIT + ".checkTestSuite"; //$NON-NLS-1$

    public static final String CHECK_TEST_SUITE_NONE = "*NONE"; //$NON-NLS-1$

    public static final String CHECK_TEST_SUITE_TEXT = "*TEXT"; //$NON-NLS-1$

    public static final String CHECK_TEST_SUITE_ATTRIBUTE = "*ATTRIBUTE"; //$NON-NLS-1$

    /**
     * Values controlling debug settings.
     */
    public static final String DEBUG = RPGUNIT + ".debug"; //$NON-NLS-1$

    public static final String DEBUG_CONNECTION = REPORT + ".connection"; //$NON-NLS-1$

    public static final Boolean DEBUG_CONNECTION_NEW = true;

    public static final Boolean DEBUG_CONNECTION_REUSE = false;

    public static final String DEBUG_POSITION_TO_LINE = REPORT + ".positionToLine"; //$NON-NLS-1$

    public static final Boolean DEBUG_POSITION_TO_LINE_YES = true;

    public static final Boolean DEBUG_POSITION_TO_LINE_NO = false;

    /**
     * Private constructor to ensure the Singleton pattern.
     */
    private Preferences() {
    }

    /**
     * Thread-safe method that returns the instance of this Singleton class.
     */
    public synchronized static Preferences getInstance() {
        if (instance == null) {
            instance = new Preferences();
            preferenceStore = RPGUnitCorePlugin.getDefault().getPreferenceStore();
            instance.initialize();
        }
        return instance;
    }

    /*
     * Preferences: GETTER
     */

    public String getOutput() {
        String tOutput = preferenceStore.getString(OUTPUT);
        if (isValidOutput(tOutput)) {
            return tOutput;
        }
        return getDefaultOutput();
    }

    public String getDetail() {
        String tDetail = preferenceStore.getString(REPORT_DETAIL);
        if (isValidDetail(tDetail)) {
            return tDetail;
        }
        return getDefaultDetail();
    }

    public String getRunOrder() {
        String tOrder = preferenceStore.getString(RUN_ORDER);
        if (isValidOrder(tOrder)) {
            return tOrder;
        }
        return getDefaultRunOrder();
    }

    public String[] getLibraryList() {
        String[] tLibraryList = new String[1];
        tLibraryList[0] = preferenceStore.getString(LIBRARY_LIST);
        if (isValidLibraryList(tLibraryList)) {
            return tLibraryList;
        }
        return new String[] { getDefaultLibraryList() };
    }

    public I5ObjectName getJobDescription() {
        String tName = preferenceStore.getString(JOBD_NAME);
        String tLibrary = preferenceStore.getString(JOBD_LIBRARY);
        I5ObjectName tJobDescription = new I5ObjectName(tName, tLibrary);

        if (isValidJobDescription(tJobDescription)) {
            return tJobDescription;
        }
        tJobDescription = getDefaultJobDescription();
        return tJobDescription;
    }

    public String getReclaimResources() {
        String tRclRsc = preferenceStore.getString(RECLAIM_RESOURCES);
        if (isValidReclaimResources(tRclRsc)) {
            return tRclRsc;
        }
        return getReclaimResources();
    }

    public boolean isReportDisabled() {
        boolean tIsDisabled = preferenceStore.getBoolean(REPORT_DISABLED);
        return tIsDisabled;
    }

    public String getProductLibrary() {
        String tProductLibrary = preferenceStore.getString(PRODUCT_LIBRARY);
        return tProductLibrary;
    }

    public String getCheckTestSuite() {
        String tCheckTestSuite = preferenceStore.getString(CHECK_TEST_SUITE);
        return tCheckTestSuite;
    }

    public boolean mustCreateNewConnection() {
        boolean tCreateNewConnection = preferenceStore.getBoolean(DEBUG_CONNECTION);
        return tCreateNewConnection;
    }

    public boolean shallPositionToLine() {
        boolean tPositionToLine = preferenceStore.getBoolean(DEBUG_POSITION_TO_LINE);
        return tPositionToLine;
    }

    public boolean isShowWarningMessage(String warningKey) {
        boolean tShowWarning = preferenceStore.getBoolean(warningKey);
        return tShowWarning;
    }

    public boolean isAnyWarningMessageDisabled() {
        if (preferenceStore.getBoolean(WARN_MESSAGE_SRC_OPTION) && preferenceStore.getBoolean(WARN_MESSAGE_USER_DEFINED_ATTRIBUTE)
            && preferenceStore.getBoolean(WARN_MESSAGE_OBSOLETE_PLUGINS_V1)) {
            return false;
        }
        return true;
    }

    public int getPreferencesVersionNumber() {
        int version;
        try {
            version = preferenceStore.getInt(PREFERENCES_VERSION_NUMBER);
        } catch (Throwable e) {
            version = getDefaultPreferencesVersionNumber();
        }
        return version;
    }

    /*
     * Preferences: SETTER
     */

    public void setOutput(String anOutput) {
        saveOutput(anOutput);
    }

    public void setDetail(String aDetail) {
        saveDetail(aDetail);
    }

    public void setRunOrder(String anOrder) {
        saveOrder(anOrder);
    }

    public void setLibraryList(String[] aLibraryList) {
        saveLibraryList(aLibraryList);
    }

    public void setJobDescription(I5ObjectName aJobDescription) {
        if (isValidJobDescription(aJobDescription)) {
            saveJobDescription(aJobDescription);
        } else {
            saveJobDescription(getDefaultJobDescription());
        }
    }

    public void setReportDisabled(boolean aDisabled) {
        saveReportDisabledState(aDisabled);
    }

    public void setDebugConnectionNew(boolean aCreateNew) {
        saveDebugConnectionState(aCreateNew);
    }

    public void setPositionToLine(boolean aPositionToLine) {
        savePositionToLineState(aPositionToLine);
    }

    public void setProductLibrary(String aProduductLibrary) {
        saveProductLibrary(aProduductLibrary);
    }

    public void setCheckTestSuite(String aCheckTestSuite) {
        saveCheckTestSuite(aCheckTestSuite);
    }

    public void setReclaimResources(String aRclRsc) {
        saveReclaimResources(aRclRsc);
    }

    public boolean useJobDescriptionForLibraryList() {
        String[] tLibraryList = Preferences.getInstance().getLibraryList();
        if (tLibraryList.length == 1 && LIBRARY_LIST_JOBD.equalsIgnoreCase(tLibraryList[0])) {
            return true;
        }
        return false;
    }

    public boolean useDefaultJobDescriptionForLibraryList() {
        if (!useJobDescriptionForLibraryList()) {
            return false;
        }

        I5ObjectName tJobDescription = Preferences.getInstance().getJobDescription();
        if (tJobDescription != null && JOBD_NAME_DFT.equalsIgnoreCase(tJobDescription.getName())) {
            return true;
        }
        return false;
    }

    public void setShowWarningMessage(String warningKey, boolean showWarning) {
        saveShowWarningMessage(warningKey, showWarning);
    }

    public void enableAllWarningMessages() {
        saveShowWarningMessage(WARN_MESSAGE_SRC_OPTION, true);
        saveShowWarningMessage(WARN_MESSAGE_USER_DEFINED_ATTRIBUTE, true);
        saveShowWarningMessage(WARN_MESSAGE_OBSOLETE_PLUGINS_V1, true);
    }

    public void setPreferencesVersionNumber(int versionNumber) {
        savePreferencesVersionNumber(versionNumber);
    }

    /**
     * Is called by
     * {@link PreferencesInitializer#initializeDefaultPreferences()} in order to
     * initialize the preferences default values.
     * <p>
     * This method must <b>never</b> be called from outside the
     * PreferencesInitializer class.
     */
    public void initializeDefaultPreferences() {
        preferenceStore.setDefault(OUTPUT, getDefaultOutput());
        preferenceStore.setDefault(REPORT_DETAIL, getDefaultDetail());
        preferenceStore.setDefault(RUN_ORDER, getDefaultRunOrder());
        preferenceStore.setDefault(LIBRARY_LIST, getDefaultLibraryList());
        preferenceStore.setDefault(JOBD_NAME, getDefaultJobDescription().getName());
        preferenceStore.setDefault(JOBD_LIBRARY, getDefaultJobDescription().getLibrary());
        preferenceStore.setDefault(REPORT_DISABLED, getDefaultReportDisabledState());
        preferenceStore.setDefault(DEBUG_POSITION_TO_LINE, getDefaultPositionToLineState());
        preferenceStore.setDefault(PRODUCT_LIBRARY, getDefaultProductLibrary());
        preferenceStore.setDefault(CHECK_TEST_SUITE, getDefaultCheckTestSuite());
        preferenceStore.setDefault(RECLAIM_RESOURCES, getDefaultReclaimResources());
        preferenceStore.setDefault(WARN_MESSAGE_SRC_OPTION, getDefaultShowWarnMessages());
        preferenceStore.setDefault(WARN_MESSAGE_USER_DEFINED_ATTRIBUTE, getDefaultShowWarnMessages());
        preferenceStore.setDefault(WARN_MESSAGE_OBSOLETE_PLUGINS_V1, getDefaultShowWarnMessages());
        preferenceStore.setDefault(PREFERENCES_VERSION_NUMBER, getDefaultPreferencesVersionNumber());

        preferenceStore.firePropertyChangeEvent(PRODUCT_LIBRARY, getProductLibrary(), getProductLibrary());
    }

    private boolean isValidOutput(String anOutput) {
        if (OUTPUT_ALWAYS.equals(anOutput) || OUTPUT_ERROR.equals(anOutput) || OUTPUT_NONE.equals(anOutput)) {
            return true;
        }
        return false;
    }

    private boolean isValidDetail(String aDetail) {
        if (REPORT_DETAIL_BASIC.equals(aDetail) || REPORT_DETAIL_ALL.equals(aDetail)) {
            return true;
        }
        return false;
    }

    private boolean isValidOrder(String anOrder) {
        if (RUN_ORDER_API.equals(anOrder) || RUN_ORDER_REVERSE.equals(anOrder)) {
            return true;
        }
        return false;
    }

    private boolean isValidLibraryList(String[] aLibraryList) {
        if (aLibraryList.length == 0) {
            return false;
        }

        for (int i = 0; i < aLibraryList.length; i++) {
            if (isNullOrEmpty(aLibraryList[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidJobDescription(I5ObjectName aJobDescription) {
        if (!isNullOrEmpty(aJobDescription.getName()) && !isNullOrEmpty(aJobDescription.getLibrary())) {
            return true;
        }
        return false;
    }

    private boolean isValidReclaimResources(String aRclRsc) {
        if (RECLAIM_RESOURCES_NO.equals(aRclRsc) || RECLAIM_RESOURCES_ALWAYS.equals(aRclRsc) || RECLAIM_RESOURCES_ONCE.equals(aRclRsc)) {
            return true;
        }
        return false;
    }

    private boolean isNullOrEmpty(String aValue) {
        if (aValue == null || aValue.length() == 0) {
            return true;
        }
        return false;
    }

    public String getDefaultOutput() {
        return OUTPUT_ALWAYS;
    }

    public String getDefaultDetail() {
        return REPORT_DETAIL_BASIC;
    }

    public String getDefaultRunOrder() {
        return RUN_ORDER_API;
    }

    public String getDefaultLibraryList() {
        return LIBRARY_LIST_JOBD;
    }

    public I5ObjectName getDefaultJobDescription() {
        return new I5ObjectName(JOBD_DEFAULT_NAME, JOBD_DEFAULT_LIBRARY);
    }

    public boolean getDefaultReportDisabledState() {
        return REPORT_DISABLED_TRUE;
    }

    public boolean getDefaultConnectionState() {
        return DEBUG_CONNECTION_REUSE;
    }

    public boolean getDefaultPositionToLineState() {
        return DEBUG_POSITION_TO_LINE_NO;
    }

    public String getDefaultProductLibrary() {
        return PRODUCT_LIBRARY_LIBL;
    }

    public String getDefaultReclaimResources() {
        return RECLAIM_RESOURCES_NO;
    }

    public boolean getDefaultShowWarnMessages() {
        return WARN_MESSAGE_DEFAULT;
    }

    public String getDefaultCheckTestSuite() {
        return CHECK_TEST_SUITE_TEXT;
    }

    private int getDefaultPreferencesVersionNumber() {
        return -1;
    }

    public String[] getLibraryListItems() {
        return new String[] { LIBRARY_LIST_CURRENT, LIBRARY_LIST_JOBD };
    }

    public String[] getRunOrderItems() {
        return new String[] { RUN_ORDER_API, RUN_ORDER_REVERSE };
    }

    public String[] getDetailItems() {
        return new String[] { REPORT_DETAIL_BASIC, REPORT_DETAIL_ALL };
    }

    public String[] getOutputItems() {
        return new String[] { OUTPUT_ALWAYS, OUTPUT_ERROR, OUTPUT_NONE };
    }

    public String[] getReclaimResourcesItems() {
        return new String[] { RECLAIM_RESOURCES_NO, RECLAIM_RESOURCES_ALWAYS, RECLAIM_RESOURCES_ONCE };
    }

    public String[] getCheckTestSuiteItems() {
        return new String[] { CHECK_TEST_SUITE_NONE, CHECK_TEST_SUITE_TEXT, CHECK_TEST_SUITE_ATTRIBUTE };
    }

    public IPreferenceStore getStore() {
        return preferenceStore;
    }

    private void saveOutput(String anOutput) {
        preferenceStore.setValue(OUTPUT, anOutput);
    }

    private void saveDetail(String aDetail) {
        preferenceStore.setValue(REPORT_DETAIL, aDetail);
    }

    private void saveOrder(String anOrder) {
        preferenceStore.setValue(RUN_ORDER, anOrder);
    }

    private void saveJobDescription(I5ObjectName aJobDescription) {
        preferenceStore.setValue(JOBD_NAME, aJobDescription.getName());
        preferenceStore.setValue(JOBD_LIBRARY, aJobDescription.getLibrary());
    }

    private void saveLibraryList(String[] aLibraryList) {
        preferenceStore.setValue(LIBRARY_LIST, aLibraryList[0]);
    }

    private void saveReportDisabledState(boolean aDisabled) {
        preferenceStore.setValue(REPORT_DISABLED, aDisabled);
    }

    private void saveDebugConnectionState(boolean aCreateNew) {
        preferenceStore.setValue(DEBUG_CONNECTION, aCreateNew);
    }

    private void savePositionToLineState(boolean aPositionToLine) {
        preferenceStore.setValue(DEBUG_POSITION_TO_LINE, aPositionToLine);
    }

    private void saveProductLibrary(String aProductLibrary) {
        preferenceStore.setValue(PRODUCT_LIBRARY, aProductLibrary);
    }

    private void saveCheckTestSuite(String aCheckTestSuite) {
        preferenceStore.setValue(CHECK_TEST_SUITE, aCheckTestSuite);
    }

    private void saveReclaimResources(String aRclRsc) {
        preferenceStore.setValue(RECLAIM_RESOURCES, aRclRsc);
    }

    private void saveShowWarningMessage(String aWarningKey, boolean showWarning) {
        preferenceStore.setValue(aWarningKey, showWarning);
    }

    private void savePreferencesVersionNumber(int versionNumber) {
        preferenceStore.setValue(PREFERENCES_VERSION_NUMBER, versionNumber);
    }

    /**
     * Initializes the preferences. Adds a property change listener to the
     * Eclipse preferences store to keep track of changes.
     */
    private void initialize() {

        // Add change listener to the preference store so that we are notified
        // in case of changes
        preferenceStore.addPropertyChangeListener(new PreferencesChangeListener());
    }

}