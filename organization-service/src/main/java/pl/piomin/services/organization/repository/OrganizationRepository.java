package pl.piomin.services.organization.repository;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import pl.piomin.services.organization.model.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationRepository {

	private List<Organization> organizations = new ArrayList<>();

	@WithSpan
	public Organization add(Organization organization) {
		organization.setId((long) (organizations.size()+1));
		organizations.add(organization);
		return organization;
	}
	
	@WithSpan
	public Organization findById(Long id) {
		return organizations.stream()
				.filter(a -> a.getId().equals(id))
				.findFirst()
				.orElseThrow();
	}
	
	@WithSpan
	public List<Organization> findAll() {
		return organizations;
	}
	
}
