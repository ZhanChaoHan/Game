// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   F.java

package com.crocro.wrp.bs;


public interface F
{

    public static final int NOT = -1;
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    public static final float COS[] = {
        1.0F, 0.9998477F, 0.9993908F, 0.9986296F, 0.9975642F, 0.9961949F, 0.9945222F, 0.9925466F, 0.9902686F, 0.9876891F, 
        0.9848086F, 0.9816282F, 0.9781489F, 0.9743716F, 0.9702975F, 0.9659278F, 0.961264F, 0.9563073F, 0.9510594F, 0.9455218F, 
        0.9396961F, 0.9335843F, 0.9271881F, 0.9205095F, 0.9135505F, 0.9063132F, 0.8987999F, 0.8910128F, 0.8829544F, 0.8746269F, 
        0.8660331F, 0.8571755F, 0.8480569F, 0.8386798F, 0.8290474F, 0.8191624F, 0.8090279F, 0.798647F, 0.7880228F, 0.7771586F, 
        0.7660577F, 0.7547234F, 0.7431593F, 0.7313688F, 0.7193555F, 0.7071232F, 0.6946754F, 0.6820161F, 0.669149F, 0.656078F, 
        0.6428073F, 0.6293408F, 0.6156825F, 0.6018368F, 0.5878077F, 0.5735996F, 0.5592168F, 0.5446637F, 0.5299446F, 0.5150641F, 
        0.5000268F, 0.4848371F, 0.4694997F, 0.4540194F, 0.4384007F, 0.4226486F, 0.4067677F, 0.3907629F, 0.374639F, 0.3584011F, 
        0.342054F, 0.3256027F, 0.3090522F, 0.2924076F, 0.275674F, 0.2588563F, 0.2419599F, 0.2249897F, 0.207951F, 0.1908489F, 
        0.1736887F, 0.1564756F, 0.1392149F, 0.1219117F, 0.1045715F, 0.08719933F, 0.06980063F, 0.05238068F, 0.03494477F, 0.01749821F, 
        4.632679E-005F, -0.01740557F, -0.03485217F, -0.05228815F, -0.06970821F, -0.08710703F, -0.1044793F, -0.1218198F, -0.1391231F, -0.1563841F, 
        -0.1735975F, -0.190758F, -0.2078603F, -0.2248994F, -0.24187F, -0.2587668F, -0.2755849F, -0.292319F, -0.3089641F, -0.3255151F, 
        -0.3419669F, -0.3583146F, -0.3745531F, -0.3906776F, -0.406683F, -0.4225646F, -0.4383175F, -0.4539368F, -0.4694179F, -0.4847561F, 
        -0.4999465F, -0.5149847F, -0.529866F, -0.5445859F, -0.55914F, -0.5735237F, -0.5877328F, -0.6017628F, -0.6156095F, -0.6292688F, 
        -0.6427364F, -0.6560081F, -0.6690801F, -0.6819483F, -0.6946087F, -0.7070577F, -0.7192912F, -0.7313056F, -0.7430973F, -0.7546626F, 
        -0.7659981F, -0.7771003F, -0.7879658F, -0.7985912F, -0.8089734F, -0.8191093F, -0.8289955F, -0.8386294F, -0.8480077F, -0.8571278F, 
        -0.8659868F, -0.874582F, -0.8829108F, -0.8909708F, -0.8987593F, -0.9062741F, -0.9135128F, -0.9204733F, -0.9271534F, -0.9335511F, 
        -0.9396644F, -0.9454916F, -0.9510307F, -0.9562802F, -0.9612384F, -0.9659038F, -0.970275F, -0.9743508F, -0.9781296F, -0.9816106F, 
        -0.9847925F, -0.9876746F, -0.9902557F, -0.9925353F, -0.9945126F, -0.9961869F, -0.9975577F, -0.9986247F, -0.9993876F, -0.9998461F, 
        -1F, -0.9998493F, -0.9993941F, -0.9986345F, -0.9975706F, -0.996203F, -0.9945319F, -0.9925579F, -0.9902815F, -0.9877036F, 
        -0.9848247F, -0.9816459F, -0.9781681F, -0.9743924F, -0.9703199F, -0.9659518F, -0.9612895F, -0.9563344F, -0.951088F, -0.9455519F, 
        -0.9397278F, -0.9336175F, -0.9272228F, -0.9205457F, -0.9135882F, -0.9063524F, -0.8988405F, -0.8910549F, -0.8829979F, -0.8746719F, 
        -0.8660794F, -0.8572232F, -0.8481059F, -0.8387303F, -0.8290992F, -0.8192155F, -0.8090823F, -0.7987027F, -0.7880799F, -0.7772169F, 
        -0.7661172F, -0.7547842F, -0.7432213F, -0.731432F, -0.7194199F, -0.7071887F, -0.694742F, -0.6820838F, -0.6692178F, -0.656148F, 
        -0.6428783F, -0.6294128F, -0.6157556F, -0.6019108F, -0.5878827F, -0.5736755F, -0.5592936F, -0.5447413F, -0.5300232F, -0.5151435F, 
        -0.500107F, -0.4849181F, -0.4695815F, -0.4541019F, -0.438484F, -0.4227326F, -0.4068523F, -0.3908482F, -0.374725F, -0.3584876F, 
        -0.3421411F, -0.3256903F, -0.3091404F, -0.2924962F, -0.275763F, -0.2589458F, -0.2420498F, -0.22508F, -0.2080416F, -0.1909399F, 
        -0.17378F, -0.1565672F, -0.1393066F, -0.1220037F, -0.1046636F, -0.08729163F, -0.06989306F, -0.0524732F, -0.03503736F, -0.01759085F, 
        -0.0001389804F, 0.01731293F, 0.03475957F, 0.05219562F, 0.06961577F, 0.08701473F, 0.1043872F, 0.1217278F, 0.1390314F, 0.1562926F, 
        0.1735062F, 0.190667F, 0.2077697F, 0.2248091F, 0.24178F, 0.2586773F, 0.2754958F, 0.2922304F, 0.308876F, 0.3254275F, 
        0.3418799F, 0.3582281F, 0.3744672F, 0.3905923F, 0.4065984F, 0.4224806F, 0.4382342F, 0.4538543F, 0.4693361F, 0.484675F, 
        0.4998662F, 0.5149053F, 0.5297874F, 0.5445082F, 0.5590632F, 0.5734478F, 0.5876578F, 0.6016888F, 0.6155365F, 0.6291968F, 
        0.6426654F, 0.6559382F, 0.6690112F, 0.6818805F, 0.6945421F, 0.7069921F, 0.7192268F, 0.7312424F, 0.7430353F, 0.7546018F, 
        0.7659386F, 0.777042F, 0.7879087F, 0.7985355F, 0.808919F, 0.8190561F, 0.8289437F, 0.8385789F, 0.8479586F, 0.85708F, 
        0.8659405F, 0.8745371F, 0.8828673F, 0.8909287F, 0.8987187F, 0.9062349F, 0.9134751F, 0.920437F, 0.9271187F, 0.9335179F, 
        0.9396328F, 0.9454614F, 0.9510021F, 0.9562531F, 0.9612129F, 0.9658799F, 0.9702526F, 0.9743299F, 0.9781103F, 0.9815929F, 
        0.9847764F, 0.9876601F, 0.9902428F, 0.992524F, 0.9945028F, 0.9961787F, 0.9975513F, 0.9986199F, 0.9993844F, 0.9998444F
    };
    public static final float SIN[] = {
        0.0F, 0.01745189F, 0.03489847F, 0.05233441F, 0.06975442F, 0.08715318F, 0.1045254F, 0.1218658F, 0.139169F, 0.1564299F, 
        0.1736431F, 0.1908034F, 0.2079057F, 0.2249445F, 0.2419149F, 0.2588116F, 0.2756294F, 0.2923633F, 0.3090082F, 0.3255589F, 
        0.3420105F, 0.3583578F, 0.3745961F, 0.3907202F, 0.4067253F, 0.4226066F, 0.4383591F, 0.4539781F, 0.4694588F, 0.4847966F, 
        0.4999866F, 0.5150244F, 0.5299053F, 0.5446248F, 0.5591784F, 0.5735617F, 0.5877703F, 0.6017998F, 0.6156461F, 0.6293048F, 
        0.6427718F, 0.6560431F, 0.6691145F, 0.6819822F, 0.6946421F, 0.7070904F, 0.7193233F, 0.7313372F, 0.7431283F, 0.754693F, 
        0.7660279F, 0.7771294F, 0.7879943F, 0.7986191F, 0.8090007F, 0.8191358F, 0.8290215F, 0.8386546F, 0.8480323F, 0.8571517F, 
        0.86601F, 0.8746045F, 0.8829326F, 0.8909918F, 0.8987796F, 0.9062936F, 0.9135317F, 0.9204914F, 0.9271708F, 0.9335677F, 
        0.9396803F, 0.9455067F, 0.951045F, 0.9562938F, 0.9612512F, 0.9659159F, 0.9702863F, 0.9743611F, 0.9781393F, 0.9816194F, 
        0.9848006F, 0.9876818F, 0.9902622F, 0.992541F, 0.9945174F, 0.9961909F, 0.997561F, 0.9986272F, 0.9993892F, 0.9998469F, 
        1.0F, 0.9998485F, 0.9993924F, 0.998632F, 0.9975674F, 0.996199F, 0.994527F, 0.9925522F, 0.9902751F, 0.9876963F, 
        0.9848167F, 0.9816371F, 0.9781585F, 0.974382F, 0.9703087F, 0.9659398F, 0.9612767F, 0.9563209F, 0.9510737F, 0.9455369F, 
        0.939712F, 0.9336009F, 0.9272054F, 0.9205276F, 0.9135693F, 0.9063328F, 0.8988202F, 0.8910339F, 0.8829761F, 0.8746494F, 
        0.8660563F, 0.8571994F, 0.8480814F, 0.8387051F, 0.8290733F, 0.819189F, 0.8090551F, 0.7986748F, 0.7880513F, 0.7771878F, 
        0.7660875F, 0.7547538F, 0.7431903F, 0.7314004F, 0.7193877F, 0.7071559F, 0.6947087F, 0.6820499F, 0.6691834F, 0.656113F, 
        0.6428428F, 0.6293768F, 0.6157191F, 0.6018738F, 0.5878452F, 0.5736375F, 0.5592552F, 0.5447025F, 0.5299839F, 0.5151038F, 
        0.5000669F, 0.4848776F, 0.4695407F, 0.4540607F, 0.4384424F, 0.4226906F, 0.40681F, 0.3908055F, 0.374682F, 0.3584444F, 
        0.3420975F, 0.3256465F, 0.3090963F, 0.2924519F, 0.2757185F, 0.2589011F, 0.2420048F, 0.2250348F, 0.2079963F, 0.1908944F, 
        0.1737344F, 0.1565214F, 0.1392608F, 0.1219577F, 0.1046175F, 0.08724548F, 0.06984685F, 0.05242694F, 0.03499106F, 0.01754453F, 
        9.265359E-005F, -0.01735925F, -0.03480587F, -0.05224189F, -0.06966199F, -0.08706088F, -0.1044332F, -0.1217738F, -0.1390773F, -0.1563384F, 
        -0.1735519F, -0.1907125F, -0.207815F, -0.2248543F, -0.241825F, -0.2587221F, -0.2755404F, -0.2922747F, -0.3089201F, -0.3254713F, 
        -0.3419234F, -0.3582714F, -0.3745102F, -0.390635F, -0.4066407F, -0.4225226F, -0.4382758F, -0.4538956F, -0.469377F, -0.4847155F, 
        -0.4999064F, -0.514945F, -0.5298267F, -0.5445471F, -0.5591016F, -0.5734858F, -0.5876953F, -0.6017258F, -0.615573F, -0.6292328F, 
        -0.6427009F, -0.6559732F, -0.6690457F, -0.6819144F, -0.6945754F, -0.7070249F, -0.719259F, -0.731274F, -0.7430663F, -0.7546322F, 
        -0.7659683F, -0.7770711F, -0.7879372F, -0.7985633F, -0.8089462F, -0.8190827F, -0.8289697F, -0.8386041F, -0.8479832F, -0.8571039F, 
        -0.8659636F, -0.8745596F, -0.8828891F, -0.8909497F, -0.898739F, -0.9062545F, -0.9134939F, -0.9204552F, -0.927136F, -0.9335345F, 
        -0.9396486F, -0.9454765F, -0.9510164F, -0.9562667F, -0.9612256F, -0.9658918F, -0.9702638F, -0.9743403F, -0.97812F, -0.9816017F, 
        -0.9847845F, -0.9876673F, -0.9902493F, -0.9925296F, -0.9945077F, -0.9961828F, -0.9975545F, -0.9986224F, -0.999386F, -0.9998453F, 
        -1F, -0.9998501F, -0.9993957F, -0.9986369F, -0.9975739F, -0.996207F, -0.9945368F, -0.9925635F, -0.990288F, -0.9877108F, 
        -0.9848328F, -0.9816548F, -0.9781778F, -0.9744028F, -0.9703311F, -0.9659638F, -0.9613023F, -0.9563479F, -0.9511023F, -0.945567F, 
        -0.9397436F, -0.9336341F, -0.9272401F, -0.9205638F, -0.913607F, -0.906372F, -0.8988608F, -0.8910759F, -0.8830196F, -0.8746943F, 
        -0.8661026F, -0.8572471F, -0.8481305F, -0.8387555F, -0.829125F, -0.8192421F, -0.8091096F, -0.7987306F, -0.7881083F, -0.7772461F, 
        -0.766147F, -0.7548146F, -0.7432523F, -0.7314636F, -0.7194521F, -0.7072214F, -0.6947753F, -0.6821177F, -0.6692522F, -0.6561829F, 
        -0.6429138F, -0.6294488F, -0.6157921F, -0.6019478F, -0.5879202F, -0.5737135F, -0.559332F, -0.5447802F, -0.5300624F, -0.5151832F, 
        -0.5001471F, -0.4849586F, -0.4696224F, -0.4541432F, -0.4385257F, -0.4227745F, -0.4068946F, -0.3908908F, -0.3747679F, -0.3585308F, 
        -0.3421846F, -0.3257341F, -0.3091844F, -0.2925406F, -0.2758076F, -0.2589906F, -0.2420947F, -0.2251251F, -0.2080869F, -0.1909853F, 
        -0.1738256F, -0.1566129F, -0.1393525F, -0.1220497F, -0.1047097F, -0.08733778F, -0.06993928F, -0.05251947F, -0.03508366F, -0.01763717F
    };

}