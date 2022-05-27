export interface IEquipamento {
  id?: number;
  nome?: string | null;
  status?: string | null;
  numeroSerie?: string | null;
}

export const defaultValue: Readonly<IEquipamento> = {};
