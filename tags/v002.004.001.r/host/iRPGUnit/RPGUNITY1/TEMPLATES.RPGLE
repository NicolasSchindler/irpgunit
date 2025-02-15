      /if defined(templates)
      /eof
      /endif
      /define templates
      //
      // RPGUnit Data Templates.
      //

     D TEST_PREFIX     c                   const('TEST')

     D TSTPRC_ALL      c                   '*ALL'

     D ORDER_API       c                   '*API'
     D ORDER_REVERSE   c                   '*REVERSE'

     D DETAIL_BASIC    c                   '*BASIC'
     D DETAIL_ALL      c                   '*ALL'

     D OUTPUT_NONE     c                   '*NONE'
     D OUTPUT_ERROR    c                   '*ERROR'
     D OUTPUT_ALLWAYS  c                   '*ALLWAYS'

     D RCLRSC_NO       c                   '*NO'
     D RCLRSC_ALWAYS   c                   '*ALWAYS'
     D RCLRSC_ONCE     c                   '*ONCE'

       // Source member types
     D MBR_RPGLE       c                   const('RPGLE')
     D MBR_SQLRPGLE    c                   const('SQLRPGLE')
     D PARM_DBGVIEW    c                   const('DBGVIEW')

     D order_t         s             10a   based(template)
     D detail_t        s             10a   based(template)
     D output_t        s             10a   based(template)
     D rclrsc_t        s             10a   based(template)

     D MAX_NUM_LIB     c                   250

     D LibL_t          ds                  based(template)
     D  numE                   1      2i 0
     D  lib                    3   2502a   dim(MAX_NUM_LIB)

     D LiblData_t      ds                  qualified template
     D  curLib                 1     10a
     D  libL                               likeds(LibL_t)

       // ILE Activation Mark.
     D ActMark_t       s             10i 0 based(template)

       // Empty Assertion Failure Event of version 1.
     D EMPTY_ASSERT_FAIL_EVT...
     D                 ds                  likeds(AssertFailEvt_t) inz

     D EMPTY_ASSERT_FAIL_EVT_LONG...
     D                 ds                  likeds(AssertFailEvtLong_t) inz

       // Assertion Failure Event.
     D AssertFailEvtLong_t...
     D                 ds                  qualified based(template)
     D  msg                                like(msgText_t)
     D  callStk                            likeds(CallStk_t)

       // Assertion Failure Event of version 1
     D assertFailEvt_t...
     D                 ds                  qualified based(template)
     D  msg                         256a   varying
     D  callStk                            likeds(CallStk_t)

       // Call Stack.
     D CallStk_t       ds                  qualified based(template)
     D  numE                         10i 0
     D  entry                              likeds(CallStkEnt_t)
     D                                     Dim(MAX_CALL_STK_SIZE)

       // Call Stack Entry.
     D CallStkEnt_t    ds                  qualified based(template)
     D  level                        10i 0
     D  sender                             likeds(MsgSender_t)

       // Program Message.
     D Msg_t           ds                  qualified based(template)
     D  id                            7a
     D  txt                                like(msgText_t)
     D  rplData                     256a   varying
     D  sender                             likeds(MsgSender_t)

       // Sender of a program message
     D MsgSender_t     ds                  qualified based(template)
     D  qPgm                               likeds(Object_t)
     D  qMod                               likeds(Object_t)
     D  procNm                             like(ProcNm_t)
     D  specNb                       10a

       // Object qualified Name.
     D Object_t        ds                  qualified based(template)
     D  nm                           10a
     D  lib                          10a

       // Spooled file qualified Name.
     D SplF_t          ds                  qualified based(template)
     D  system                       10a
     D  nm                           10a
     D  nbr                          10i 0
     D  job                                likeds(Job_t)

       // Job qualified Name.
     D Job_t           ds                  qualified based(template)
     D  name                         10A
     D  user                         10A
     D  nbr                           6A

       // Named callable procedure.
     D Proc_t          ds                  qualified based(template)
     D  procNm                             like(ProcNm_t)
     D  procPtr                        *   procptr

     D ProcNmList_t    ds                  qualified based(template)
     D  handle                         *
     D  cnt                            *   procptr
     D  getNm                          *   procptr
     D  goToNext                       *   procptr

     D ProcNm_t        s            256a   varying based(template)

     D ProcNms_t       ds                  qualified based(template)
     D  numE                          5i 0
     D  name                               like(ProcNm_t) Dim(250)

       // qualified Job Name.
     D QlfJobNm_t      ds                  qualified based(template)
     D  jobNm                        10a
     D  usrNm                        10a
     D  jobNb                         6a

     D TestResult_t    ds                  qualified based(template)
     D  outcome                       1a
     D  details
     D  failure                            likeds(AssertFailEvtLong_t)
     D                                     Overlay(details)
     D  error                              likeds(Msg_t)
     D                                     Overlay(details)
     D  testName                           like(ProcNm_t)
     D  execTime                     20i 0
     D  srcFile                      10a
     D  srcLib                       10a
     D  srcMbr                       10a
     D  assertCnt                    10i 0

     D TestSuite_t     ds                  qualified based(template)
     D  setUpSuite                         likeds(Proc_t)
     D  setUp                              likeds(Proc_t)
     D  testCasesCnt                 10i 0
     D  testList                       *
     D  tearDown                           likeds(Proc_t)
     D  teardownSuite                      likeds(Proc_t)
     D  testResults                    *

       // User profile name.
     D UsrNm_t         s             10a   based(template)

       // Object description
     D Text_t          s             50a   varying based(template)

       // String value (must be greater than COMPILEOPT)
     D String_t...
     D                 s           5120a   varying based(template)

       // Value of a line of the log.
     D Line_t          s            256a   varying based(template)

       // Message replacement data or message text.
     D msgText_t       s           1024a   varying based(template)

       // Full qualified source member.
     D SrcMbr_t        ds                  qualified based(template)
     D  file                         10a
     D  lib                          10a
     D  mbr                          10a

     D MAX_CALL_STK_SIZE...
     D                 c                   const(64)
     D CALL_STACK_INCOMPLETE...
     D                 c                   const('*INCOMPLETE')

       // TestResult_t.outcome can have three values.
     D TEST_CASE_SUCCESS...
     D                 c                   const('S')
     D TEST_CASE_FAILURE...
     D                 c                   const('F')
     D TEST_CASE_ERROR...
     D                 c                   const('E')

     D QUOTE           c                   const('''')

