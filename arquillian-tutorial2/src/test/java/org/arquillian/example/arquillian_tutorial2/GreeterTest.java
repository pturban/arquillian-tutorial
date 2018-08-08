package org.arquillian.example.arquillian_tutorial2;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RunWith(Arquillian.class)
public class GreeterTest {
	
	private static Logger LOG = LoggerFactory.getLogger(GreeterTest.class);
	
	// @Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war");
            // war.addPackage(KohsukeGitHubServiceFactoryImpl.class.getPackage())
            // .addClasses(GitHubTestCredentials.class, GitHubServiceSpi.class, AbstractGitService.class)
            // libraries will include all classes/interfaces from the API project.
            //.addAsLibraries(dependencies)
            //.addAsLibraries(testDependencies);
	    	
				war.addClass(Greeter.class)
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
				 System.out.println(war.toString(true)); // you can print the archive to stdout (i.e., the console) before returning it
				
            // Show the deployed structure
	    	//log.fine(war.toString(true));
	    return war;
	}
	
    @Deployment
    public static JavaArchive createDeployment__() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar");
		jar.addClass(Greeter.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        	// .addAsManifestResource("test-persistence.xml", "persistence.xml");
        System.out.println(jar.toString(true)); // you can print the archive to stdout (i.e., the console) before returning it
        LOG.debug(jar.toString(true));
		return jar;
    }
	
//    @PersistenceContext
//    EntityManager em;

	
	@Inject  // javax.inject.Inject!
    Greeter greeter;

	@Test
	public void should_create_greeting() {
		LOG.debug("should_create_greeting() is called.");
	    Assert.assertEquals("Hello, Earthling!",
	        greeter.createGreeting("Earthling"));
	    greeter.greet(System.out, "Earthling");
	    System.out.println("DONE!");
	}


}
