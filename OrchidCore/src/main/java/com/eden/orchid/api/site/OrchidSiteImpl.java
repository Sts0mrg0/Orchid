package com.eden.orchid.api.site;

import com.eden.orchid.api.OrchidContext;
import com.eden.orchid.api.options.annotations.Archetype;
import com.eden.orchid.api.options.annotations.Description;
import com.eden.orchid.api.options.annotations.Option;
import com.eden.orchid.api.options.archetypes.ConfigArchetype;
import com.eden.orchid.utilities.OrchidUtils;
import com.google.inject.name.Named;
import org.json.JSONObject;

import javax.inject.Inject;
import java.nio.file.Paths;

@Description(value = "The global configurations for your Orchid site.", name = "Site")
@Archetype(value = ConfigArchetype.class, key = "site")
public final class OrchidSiteImpl implements OrchidSite {
    private OrchidContext context;
    private final String orchidVersion;
    private final String currentWorkingDirectory;
    private final String sourceDir;
    private final String destinationDir;
    private String version;
    private String baseUrl;
    private String environment;
    private String defaultTemplateExtension;
    @Option
    @Description("Basic, common information about your site, mostly for display and SEO purposes.")
    private SiteInfo about;

    @Inject
    public OrchidSiteImpl(
            String orchidVersion,
            @Named("version") String version,
            @Named("baseUrl") String baseUrl,
            @Named("environment") String environment,
            @Named("defaultTemplateExtension") String defaultTemplateExtension,
            @Named("src") String sourceDir,
            @Named("dest") String destinationDir
    ) {
        this.orchidVersion = orchidVersion;
        this.currentWorkingDirectory = OrchidUtils.normalizePath(Paths.get(".").toAbsolutePath().normalize().toString());
        this.version = version;
        setBaseUrl(baseUrl);
        this.environment = environment;
        this.defaultTemplateExtension = defaultTemplateExtension;
        this.sourceDir = sourceDir;
        this.destinationDir = destinationDir;
    }

    @Override
    public void initialize(OrchidContext context) {
        this.context = context;
    }

    @Override
    public void setBaseUrl(String baseUrl) {
        if (baseUrl.equals("/")) {
            this.baseUrl = baseUrl;
        } else {
            this.baseUrl = (baseUrl.startsWith("/"))
                    ? "/" + OrchidUtils.normalizePath(baseUrl)
                    : OrchidUtils.normalizePath(baseUrl);
        }
    }

    @Override
    public boolean isDebug() {
        return !isProduction();
    }

    @Override
    public boolean isProduction() {
        return this.getEnvironment().equalsIgnoreCase("prod") || this.getEnvironment().equalsIgnoreCase("production");
    }

    @Override
    public SiteInfo getSiteInfo() {
        return about;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject site = new JSONObject();
        site.put("orchidVersion", orchidVersion);
        site.put("version", version);
        site.put("baseUrl", baseUrl);
        site.put("environment", environment);
        return site;
    }

    @Override
    public String toString() {
        return this.toJSON().toString();
    }

    public String getOrchidVersion() {
        return this.orchidVersion;
    }

    public String getCurrentWorkingDirectory() {
        return this.currentWorkingDirectory;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(final String environment) {
        this.environment = environment;
    }

    public String getDefaultTemplateExtension() {
        return this.defaultTemplateExtension;
    }

    public void setDefaultTemplateExtension(final String defaultTemplateExtension) {
        this.defaultTemplateExtension = defaultTemplateExtension;
    }

    public SiteInfo getAbout() {
        return this.about;
    }

    public void setAbout(final SiteInfo about) {
        this.about = about;
    }

    @Override
    public String getSourceDir() {
        return sourceDir;
    }

    @Override
    public String getDestinationDir() {
        return destinationDir;
    }
}
