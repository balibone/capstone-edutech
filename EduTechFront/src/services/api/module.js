const getModuleDetails = (moduleCode) => {
  const fakeModuleDetails = {
    id: 1,
    code: moduleCode,
    name: 'Project Management',
    img: 'module.png',
  };
  return fakeModuleDetails;
};

const getUserModules = (userId) => {
  const fakeUserModules = [
    {
      code: 'IS4100',
      name: 'Project Management',
    },
    {
      code: 'IS3106',
      name: 'Frontend Development',
    },
    {
      code: 'IS4103',
      name: 'Capstone Project',
    },
  ];
  return fakeUserModules;
};

export { getModuleDetails, getUserModules };
