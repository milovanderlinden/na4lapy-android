/*
 *	Copyright 2017 Stowarzyszenie Na4Łapy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package pl.kodujdlapolski.na4lapy.service.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.kodujdlapolski.na4lapy.service.api.ApiService;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.service.user.UserService;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryModuleTest {

    @Mock
    private ApiService apiService;

    @Mock
    private DatabaseRepository databaseRepository;

    @Mock
    private UserService userService;

    @Mock
    private PreferencesService preferencesService;

    private RepositoryModule repositoryModule;

    @Before
    public void setUp() throws Exception {
        repositoryModule = new RepositoryModule();
    }

    @Test
    public void testProvideRepositoryService() throws Exception {
        // when
        RepositoryService result = repositoryModule.provideRepositoryService(apiService, databaseRepository, preferencesService, userService);

        // then
        assertNotNull(result);
    }
}