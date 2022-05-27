import { IUser } from 'app/shared/model/user.model';

export interface IInfoAdicional {
  id?: number;
  nome?: string | null;
  sobrenome?: string | null;
  nascimento?: string | null;
  telefoneCelular?: string | null;
  doc?: string | null;
  cep?: string | null;
  logradouro?: string | null;
  numero?: string | null;
  complemento?: string | null;
  bairro?: string | null;
  cidade?: string | null;
  estado?: string | null;
  situacao?: string | null;
  tipoLesao?: string | null;
  detalhes?: string | null;
  imagemPerfilContentType?: string | null;
  imagemPerfil?: string | null;
  imagemComDocContentType?: string | null;
  imagemComDoc?: string | null;
  imagemLogoParceiroContentType?: string | null;
  imagemLogoParceiro?: string | null;
  areaEmpresa?: string | null;
  cnpj?: string | null;
  inscricaoEstadual?: string | null;
  tipoServico?: string | null;
  razaoSocial?: string | null;
  nomeFantasia?: string | null;
  banco?: string | null;
  bancoOutro?: string | null;
  agencia?: string | null;
  numeroConta?: string | null;
  telefoneEmpresa?: string | null;
  emailEmpresa?: string | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IInfoAdicional> = {};
